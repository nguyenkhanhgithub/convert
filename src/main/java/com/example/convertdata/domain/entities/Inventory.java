package com.example.convertdata.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "inventory", schema = "products")
public class Inventory implements Cloneable {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    @MapsId
    private ProductVariant variant;

    @Column(name = "initial_quantity")
    private Integer initialQuantity;

    @Column(name = "in_quantity")
    private Integer inQuantity;

    @Column(name = "out_quantity")
    private int outQuantity;
}
