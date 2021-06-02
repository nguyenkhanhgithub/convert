package com.example.convertdata.domain.services;

import com.example.convertdata.domain.data.ShopOrderStats;
import com.example.convertdata.domain.entities.Product;
import com.example.convertdata.domain.entities.ShopOrder;
import com.example.convertdata.domain.entities.mongo.ShopOrderTotalStats;
import com.example.convertdata.domain.entities.types.OrderState;
import com.example.convertdata.domain.entities.types.OrderType;
import com.example.convertdata.domain.repositories.ProductRepository;
import com.example.convertdata.domain.repositories.ShopOrderRepository;
import com.example.convertdata.domain.repositories.mongo.ShopOrderTotalStatsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ConvertService {
  @Autowired protected ShopOrderRepository shopOrderRepository;
  @Autowired protected ProductRepository productRepository;

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

  public ResponseEntity<?> product(Pageable pageable) {
    Page<Product> products = productRepository.findAllByIdNotNull(pageable);
    return ResponseEntity.ok(products);
  }

}
