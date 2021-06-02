package com.example.convertdata.domain.data;

import lombok.Data;

@Data
public class ShopOrderStats {
  private Integer id;
  private Integer totalOrder;
  private Integer totalOrderDraft;
  private Integer totalOrderNew;
  private Integer totalOrderConfirmed;
  private Integer totalOrderShipping;
  private Integer totalOrderFinished;
  private Integer totalOrderCanceled;
  private Integer totalOrderReturned;
  private Integer totalOrderAuction;

  public ShopOrderStats(int id, Long totalOrder,
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

  }
}
