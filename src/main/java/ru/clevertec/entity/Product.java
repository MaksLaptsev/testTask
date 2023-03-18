package ru.clevertec.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String maker;
    private double height;
    private double width;
    private double length;
    private double weight;
    private double price;
    private boolean isDiscount;
    @ManyToMany(mappedBy = "listProduct")
    private List<Cart> carts;

}
