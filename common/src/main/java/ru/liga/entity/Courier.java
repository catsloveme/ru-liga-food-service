package ru.liga.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.liga.enums.StatusCourier;

@Entity
@Table(name = "couriers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "courier_id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusCourier status;

    @Column(name = "phone")
    private String phone;

    @Column(name = "coordinates")
    private String coordinates;

}
