package com.example.convertdata.domain.entities;

import com.example.convertdata.domain.entities.types.OrderState;
import com.example.convertdata.domain.entities.types.OrderType;
import com.example.convertdata.domain.entities.types.ProductState;
import com.example.convertdata.domain.entities.types.ProductType;
import com.example.convertdata.domain.utils.PostgreSQLEnumType;
import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name = "product", schema = "products")
@TypeDef(name = "pg-enum", typeClass = PostgreSQLEnumType.class)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "shop_id")
  private Integer shopId;


  @Enumerated(value = EnumType.STRING)
  @Type(type = "pg-enum")
  @Column(nullable = false, columnDefinition = "product_type", name = "type")
  private ProductType type;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<ProductVariant> variants;

  @Enumerated(value = EnumType.STRING)
  @Type(type = "pg-enum")
  @Column(nullable = false, columnDefinition = "product_state", name = "state")
  private ProductState state;

}
