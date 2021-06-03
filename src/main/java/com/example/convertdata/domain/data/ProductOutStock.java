package com.example.convertdata.domain.data;

import lombok.Data;

@Data
public class ProductOutStock {
    private Integer product_id;
    private Integer shop_id;
    private Integer init_qty;
    private Integer in_qty;
    private Integer out_qty;
    private Integer product_out_stock;
}
