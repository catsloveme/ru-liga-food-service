package ru.liga.entity;

import lombok.Data;
import ru.liga.enums.StatusCourier;
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
    private StatusCourier status;

    @Column(name = "phone")
    private String phone;

    @Column(name = "coordinates")
    private String coordinates;




}
