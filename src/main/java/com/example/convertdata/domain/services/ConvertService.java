package com.example.convertdata.domain.services;

import com.example.convertdata.domain.data.*;
import com.example.convertdata.domain.entities.Product;
import com.example.convertdata.domain.entities.ShopOrder;
import com.example.convertdata.domain.entities.mongo.ShopOrderTotalStats;
import com.example.convertdata.domain.entities.mongo.ShopProductStats;
import com.example.convertdata.domain.entities.types.OrderState;
import com.example.convertdata.domain.entities.types.OrderType;
import com.example.convertdata.domain.entities.types.ProductState;
import com.example.convertdata.domain.entities.types.ProductType;
import com.example.convertdata.domain.repositories.ProductRepository;
import com.example.convertdata.domain.repositories.ShopOrderRepository;
import com.example.convertdata.domain.repositories.mongo.ShopOrderTotalStatsRepository;
import com.example.convertdata.domain.repositories.mongo.ShopProductTotalStatsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ConvertService {
  @Autowired protected ShopOrderRepository shopOrderRepository;
  @Autowired protected ProductRepository productRepository;
  @Autowired protected ShopProductTotalStatsRepository shopProductTotalStatsRepository;

  //mongo
  @Autowired protected ShopOrderTotalStatsRepository shopOrderTotalStatsRepository;

  public ResponseEntity<?> shopOrder() {

    List<ShopOrder> shopOrders = shopOrderRepository.findAllByIdNotNull();

    Map<Integer, List<ShopOrder>> shopOrderMaps = shopOrders.stream().collect(Collectors.groupingBy(ShopOrder::getShopId, Collectors.toList()));

    List<ShopOrderStats> responses = new ArrayList<>();


    for (Map.Entry<Integer, List<ShopOrder>> entry : shopOrderMaps.entrySet()) {

      Long totalOrder = (long) entry.getValue().size();
      Long totalOrderDraft = entry.getValue().stream().filter(x -> x.getState().equals(OrderState.DRAFT)).count();
      Long totalOrderNew = entry.getValue().stream().filter(x -> x.getState().equals(OrderState.NEW)).count();
      Long totalOrderConfirmed = entry.getValue().stream().filter(x -> x.getState().equals(OrderState.CONFIRMED)).count();
      Long totalOrderShipping = entry.getValue().stream().filter(x -> x.getState().equals(OrderState.SHIPPING)).count();
      Long totalOrderFinished = entry.getValue().stream().filter(x -> x.getState().equals(OrderState.FINISHED)).count();
      Long totalOrderCanceled = entry.getValue().stream().filter(x -> x.getState().equals(OrderState.CANCELED)).count();
      Long totalOrderAuction = entry.getValue().stream().filter(x -> x.getType().equals(OrderType.AUCTION)).count();


      ShopOrderTotalStats shopOrderTotalStats = shopOrderTotalStatsRepository.findShopOrderTotalStatsById(entry.getKey());

      if (shopOrderTotalStats == null) {
        shopOrderTotalStats = new ShopOrderTotalStats(entry.getKey(), totalOrder, totalOrderDraft, totalOrderNew,
                totalOrderConfirmed, totalOrderShipping, totalOrderFinished, totalOrderCanceled, 0L, totalOrderAuction);
      } else {
        shopOrderTotalStats.assignFrom(totalOrder, totalOrderDraft, totalOrderNew,
                totalOrderConfirmed, totalOrderShipping, totalOrderFinished, totalOrderCanceled, 0L, totalOrderAuction);
      }

      shopOrderTotalStatsRepository.save(shopOrderTotalStats);


      ShopOrderStats shopOrderStats = new ShopOrderStats(entry.getKey(), totalOrder, totalOrderDraft, totalOrderNew,
              totalOrderConfirmed, totalOrderShipping, totalOrderFinished, totalOrderCanceled, 0L, totalOrderAuction);
      responses.add(shopOrderStats);
    }

    return ResponseEntity.ok(responses);
  }

  public ResponseEntity<?> product(ListProduct listProduct) {
    List<ShopProductStats> list = new ArrayList<>();
    for (ProductStats productStats : listProduct.getProductStats()) {
      ShopProductStats shopProductStats = shopProductTotalStatsRepository.findById(productStats.getShop_id()).orElse(null);
      if (Objects.isNull(shopProductStats)) {
        shopProductStats = new ShopProductStats(productStats);
      } else {
        shopProductStats.assignFrom(productStats);
      }
      shopProductStats.setUpdatedAt(LocalDateTime.now());
      list.add(shopProductStats);
    }
    shopProductTotalStatsRepository.saveAll(list);
    return ResponseEntity.ok(list);
  }

  public ResponseEntity<?> productOutStock(ListProductOutStock listProductOutStock) {
    Map<Integer, List<ProductOutStock>> productOutStockMap =
            listProductOutStock.getProductOutStocks().stream().collect(Collectors.groupingBy(ProductOutStock::getShop_id, Collectors.toList()));

    for (Map.Entry<Integer, List<ProductOutStock>> entry : productOutStockMap.entrySet()) {
      int totalValue = entry.getValue().stream().mapToInt(ProductOutStock::getProduct_out_stock).sum();
      log.info("----ShopId, product-out-stock: {}, {}", entry.getKey(), totalValue);
      ShopProductStats shopProductStats = shopProductTotalStatsRepository.findById(entry.getKey()).orElse(null);
      if (Objects.nonNull(shopProductStats)) {
        shopProductStats.setTotalProductOutStock(totalValue);
        shopProductTotalStatsRepository.save(shopProductStats);
      }
    }
    return ResponseEntity.ok(true);
  }
}
