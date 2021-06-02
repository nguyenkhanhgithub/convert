package com.example.convertdata.domain.entities;

import com.example.convertdata.domain.utils.PostgreSQLEnumType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_variant", schema = "products")
@TypeDef(name = "pg-enum", typeClass = PostgreSQLEnumType.class)
public class ProductVariant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  @JsonIgnore
  private Product product;

  // relation
  @OneToOne(mappedBy = "variant", cascade = CascadeType.ALL)
  @JoinColumn(referencedColumnName = "id")
  private Inventory inventory;
}
