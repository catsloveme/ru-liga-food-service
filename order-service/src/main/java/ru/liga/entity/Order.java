package ru.liga.entity;

import lombok.Data;
import ru.liga.enums.StatusOrder;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "status")
    private StatusOrder status;

    @Column(name = "timestamp")
    private OffsetDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @OneToMany
    @JoinColumn(name = "restaurant_menu_item_id")
    private List<RestaurantMenuItem> restaurantMenuItem;


}
