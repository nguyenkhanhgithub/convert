package com.example.convertdata.domain.entities.mongo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "v2_stats.shop_order_total_stats")
@NoArgsConstructor
@SuppressWarnings("Duplicates")
public class ShopOrderTotalStats {
  @Id
  private Integer id;

  @Field(value = "total_order")
  private Integer totalOrder;

  @Field(value = "total_order_draft")
  private Integer totalOrderDraft;

  @Field(value = "total_order_new")
  private Integer totalOrderNew;

  @Field(value = "total_order_confirmed")
  private Integer totalOrderConfirmed;

  @Field(value = "total_order_shipping")
  private Integer totalOrderShipping;

  @Field(value = "total_order_finished")
  private Integer totalOrderFinished;

  @Field(value = "total_order_canceled")
  private Integer totalOrderCanceled;

  @Field(value = "total_order_returned")
  private Integer totalOrderReturned;

  @Field(value = "total_order_auction")
  private Integer totalOrderAuction;

  @Field(value = "total_revenue")
  private Long totalRevenue;

  @Field(value = "updated_at")
  private LocalDateTime updatedAt;


  public ShopOrderTotalStats(int id, Long totalOrder,
                        Long totalOrderDraft, Long totalOrderNew,
                        Long totalOrderConfirmed, Long totalOrderShipping,
                        Long totalOrderFinished, Long totalOrderCanceled,
                        Long totalOrderReturned, Long totalOrderAuction) {
    setId(id);
    setTotalOrder(totalOrder.intValue());
    setTotalOrderDraft(totalOrderDraft.intValue());
    setTotalOrderNew(totalOrderNew.intValue());
    setTotalOrderConfirmed(totalOrderConfirmed.intValue());
    setTotalOrderShipping(totalOrderShipping.intValue());
    setTotalOrderFinished(totalOrderFinished.intValue());
    setTotalOrderCanceled(totalOrderCanceled.intValue());
    setTotalOrderReturned(totalOrderReturned.intValue());
    setTotalOrderAuction(totalOrderAuction.intValue());
    setUpdatedAt(LocalDateTime.now());
  }

  public void assignFrom(Long totalOrder,
                             Long totalOrderDraft, Long totalOrderNew,
                             Long totalOrderConfirmed, Long totalOrderShipping,
                             Long totalOrderFinished, Long totalOrderCanceled,
                             Long totalOrderReturned, Long totalOrderAuction) {
    setTotalOrder(totalOrder.intValue());
    setTotalOrderDraft(totalOrderDraft.intValue());
    setTotalOrderNew(totalOrderNew.intValue());
    setTotalOrderConfirmed(totalOrderConfirmed.intValue());
    setTotalOrderShipping(totalOrderShipping.intValue());
    setTotalOrderFinished(totalOrderFinished.intValue());
    setTotalOrderCanceled(totalOrderCanceled.intValue());
    setTotalOrderReturned(totalOrderReturned.intValue());
    setTotalOrderAuction(totalOrderAuction.intValue());
    setUpdatedAt(LocalDateTime.now());
  }
}