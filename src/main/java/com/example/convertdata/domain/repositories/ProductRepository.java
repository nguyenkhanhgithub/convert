package com.example.convertdata.domain.repositories;

import com.example.convertdata.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Page<Product> findAllByIdNotNull(Pageable pageable);
}
