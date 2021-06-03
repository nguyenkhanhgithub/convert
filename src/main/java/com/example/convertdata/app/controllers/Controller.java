package com.example.convertdata.app.controllers;

import com.example.convertdata.domain.data.ListProduct;
import com.example.convertdata.domain.data.ListProductOutStock;
import com.example.convertdata.domain.services.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/convert")
public class Controller {

  @Autowired
  ConvertService convertService;

  @GetMapping("shop_order")
  public ResponseEntity<?> shopOrder() {
    return convertService.shopOrder();
  }

  @PostMapping("product")
  public ResponseEntity<?> product(@RequestBody ListProduct listProduct) {
    return convertService.product(listProduct);
  }

  @PostMapping("product/out-stock")
  public ResponseEntity<?> productOutStock(@RequestBody ListProductOutStock productOutStock) {
    return convertService.productOutStock(productOutStock);
  }


}
