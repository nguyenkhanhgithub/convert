package com.example.convertdata.domain.data;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class ProductStats {
    @JsonProperty(value = "shop_id")
    private Integer shop_id;
    @JsonProperty(value = "total_draft")
    private Integer total_draft;
    @JsonProperty(value = "total_pending")
    private Integer total_pending;
    @JsonProperty(value = "total_ready")
    private Integer total_ready;
    @JsonProperty(value = "total_public")
    private Integer total_public;
    @JsonProperty(value = "total_reject")
    private Integer total_reject;
    @JsonProperty(value = "total_deleted")
    private Integer total_deleted;
    @JsonProperty(value = "total_report")
    private Integer total_report;
    @JsonProperty(value = "total_auction")
    public Integer total_auction;
}
