package com.example.convertdata.domain.entities.mongo;

import com.example.convertdata.domain.data.ProductStats;
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

  public ShopProductStats(ProductStats productStats) {
    setId(productStats.getShop_id());
    setTotalProductDraft(productStats.getTotal_draft());
    setTotalProductPending(productStats.getTotal_pending());
    setTotalProductReady(productStats.getTotal_ready());
    setTotalProductPublic(productStats.getTotal_public());
    setTotalProductReject(productStats.getTotal_reject());
    setTotalProductReport(productStats.getTotal_report());
    setTotalProductDeleted(productStats.getTotal_deleted());
    setTotalProductReturn(0);
    setTotalProductAuctionPublic(productStats.getTotal_auction());
    setTotalProductOutStock(0);
  }

  public void assignFrom(ProductStats productStats) {
    setTotalProductDraft(productStats.getTotal_draft());
    setTotalProductPending(productStats.getTotal_pending());
    setTotalProductReady(productStats.getTotal_ready());
    setTotalProductPublic(productStats.getTotal_public());
    setTotalProductReject(productStats.getTotal_reject());
    setTotalProductReport(productStats.getTotal_report());
    setTotalProductDeleted(productStats.getTotal_deleted());
    setTotalProductReturn(0);
    setTotalProductAuctionPublic(productStats.getTotal_auction());
    setTotalProductOutStock(0);
  }
}