package com.example.convertdata.app.controllers;

import com.example.convertdata.domain.services.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/convert")
public class Controller {

  @Autowired
  ConvertService convertService;

  @GetMapping("shop_order")
  public ResponseEntity<?> shopOrder() {
    return convertService.shopOrder();
  }

  @GetMapping("product")
  public ResponseEntity<?> product(Pageable pageable) {
    return convertService.product(pageable);
  }

}
