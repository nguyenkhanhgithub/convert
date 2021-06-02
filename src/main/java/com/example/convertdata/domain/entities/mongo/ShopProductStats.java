package com.example.convertdata.domain.entities.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "v2_stats.shop_product_stats")
@NoArgsConstructor
@SuppressWarnings("Duplicates")
public class ShopProductStats {
  @Id
  private Integer id;

  @Field(value = "total_product_draft")
  private Integer totalProductDraft;

  @Field(value = "total_product_pending")
  private Integer totalProductPending;

  @Field(value = "total_product_ready")
  private Integer totalProductReady;

  @Field(value = "total_product_public")
  private Integer totalProductPublic;

  @Field(value = "total_product_reject")
  private Integer totalProductReject;

  @Field(value = "total_product_deleted")
  private Integer totalProductDeleted;

  @Field(value = "total_product_report")
  private Integer totalProductReport;

  @Field(value = "total_product_return")
  private Integer totalProductReturn;

  @Field(value = "total_product_auction_public")
  public Integer totalProductAuctionPublic;

  @Field(value = "total_product_out_stock")
  private Integer totalProductOutStock;

  @Field(value = "updated_at")
  private LocalDateTime updatedAt;

  public ShopProductStats(int shopId, int totalProductDraft,
                          int totalProductPending, int totalProductReady,
                          int totalProductPublic, int totalProductReject, int totalProductReport,
                          int totalProductDeleted, int totalProductAuctionPublic, int totalProductOutStock) {
    setId(shopId);
    setTotalProductDraft(0);
    setTotalProductPending(0);
    setTotalProductReady(0);
    setTotalProductPublic(0);
    setTotalProductReject(0);
    setTotalProductReport(0);
    setTotalProductDeleted(0);
    setTotalProductReturn(0);
    setTotalProductAuctionPublic(0);
    setTotalProductOutStock(0);
  }

  public void assignFrom(int totalProductDraft,
                          int totalProductPending, int totalProductReady,
                          int totalProductPublic, int totalProductReject, int totalProductReport,
                          int totalProductDeleted, int totalProductAuctionPublic, int totalProductOutStock) {
    setTotalProductDraft(0);
    setTotalProductPending(0);
    setTotalProductReady(0);
    setTotalProductPublic(0);
    setTotalProductReject(0);
    setTotalProductReport(0);
    setTotalProductDeleted(0);
    setTotalProductReturn(0);
    setTotalProductAuctionPublic(0);
    setTotalProductOutStock(0);
  }
}