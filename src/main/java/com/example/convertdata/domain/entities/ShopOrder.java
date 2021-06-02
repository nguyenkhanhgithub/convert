package com.example.convertdata.domain.entities;

import com.example.convertdata.domain.entities.types.OrderState;
import com.example.convertdata.domain.entities.types.OrderType;
import com.example.convertdata.domain.utils.PostgreSQLEnumType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;


@Data
@Entity
@Table(name = "shop_order", schema = "sales")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class ShopOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "shop_id", nullable = false)
  private Integer shopId;

  @Enumerated(EnumType.STRING)
  @Type(type = "pgsql_enum")
  @Column(nullable = false)
  private OrderState state = OrderState.DRAFT;

  @Enumerated(EnumType.STRING)
  @Type(type = "pgsql_enum")
  @Column(name = "type")
  private OrderType type;
}
