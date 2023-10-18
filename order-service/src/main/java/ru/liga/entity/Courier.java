package ru.liga.entity;

import lombok.Data;
import ru.liga.enums.StatusOrder;

import javax.persistence.*;

@Entity
@Table(name = "couriers")
@Data
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "courier_id")
    private Long id;

    @Column(name = "status")
    private StatusOrder status;

    @Column(name = "phone")
    private String phone;

    @Column(name = "coordinates")
    private String coordinates;




}
