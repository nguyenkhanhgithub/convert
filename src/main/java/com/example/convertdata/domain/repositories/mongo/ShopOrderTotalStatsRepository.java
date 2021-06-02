package com.example.convertdata.domain.repositories.mongo;

import com.example.convertdata.domain.entities.ShopOrder;
import com.example.convertdata.domain.entities.mongo.ShopOrderTotalStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopOrderTotalStatsRepository extends MongoRepository<ShopOrderTotalStats, Integer> {
  ShopOrderTotalStats findShopOrderTotalStatsById(int id);
}
