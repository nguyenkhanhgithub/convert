package com.example.convertdata.domain.repositories;

import com.example.convertdata.domain.entities.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {
  List<ShopOrder> findAllByIdNotNull();
}
