package com.awbd.awbd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product extends RepresentationModel<Product> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name="title")
    @Size(max=20, message = "max 20 ch.")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private int price;
}
