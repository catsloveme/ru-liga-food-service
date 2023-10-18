package ru.liga.entity;

import lombok.Data;
import ru.liga.enums.StatusOrder;

import javax.persistence.*;
import java.time.OffsetDateTime;

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

    @Column(name = "customer_id")
    private Long customer_id;

    @Column(name = "restaurant_id")
    private Long restaurant_id;

    @Column(name = "courier_id")
    private Long courier_id;


}
