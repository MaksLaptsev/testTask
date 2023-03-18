package ru.clevertec.entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> listProduct;

    @ManyToOne
    private DiscountCard discountCard;
    private double amountWithDis;
    private double amountWithoutDis;
    private double promoDisc;
    private double cardDisc;

    public Cart(){
        this.listProduct = new ArrayList<>();
        this.discountCard = null;
    }
}
