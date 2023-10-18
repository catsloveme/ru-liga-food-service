package ru.liga.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "order_items")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    @Column(name = "restaurant_menu_item_id")
    private Long restaurantMenuItemId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

}
