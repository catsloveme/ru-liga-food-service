package ru.liga.entity;

import lombok.Data;
import ru.liga.enums.StatusRestaurant;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private StatusRestaurant status;


}
