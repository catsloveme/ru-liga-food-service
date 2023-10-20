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

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusRestaurant status;


}
