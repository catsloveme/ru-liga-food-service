package ru.liga.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import ru.liga.enums.StatusCourier;

@Entity
@Table(name = "couriers")
@Data
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "courier_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusCourier status;

    @Column(name = "phone")
    private String phone;

    @Column(name = "coordinates")
    private String coordinates;

}
