package ru.liga.entity;

import lombok.Data;
import ru.liga.enums.StatusCourier;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private StatusCourier status;


}
