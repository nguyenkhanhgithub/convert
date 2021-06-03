package com.example.convertdata.domain.repositories.mongo;

import com.example.convertdata.domain.entities.mongo.ShopProductStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopProductTotalStatsRepository extends MongoRepository<ShopProductStats, Integer> {
}
