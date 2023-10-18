package ru.liga.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "restaurant_menu_items")
@Data
public class RestaurantMenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "restaurant_menu_item_id")
    private Long id;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

}
