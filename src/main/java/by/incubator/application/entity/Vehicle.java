package by.incubator.application.entity;

import by.incubator.application.color.Color;
import by.incubator.application.infrastructure.orm.annotations.Column;
import by.incubator.application.infrastructure.orm.annotations.ID;
import by.incubator.application.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "vehicles")
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Vehicle {
    @ID
    private Long id;
    @Column(name = "typeId", nullable = false)
    private Long typeId;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "registrationNumber", unique = true, nullable = false)
    private String registrationNumber;
    @Column(name = "weight", nullable = false)
    private Integer weight;
    @Column(name = "manufactureYear")
    private Integer manufactureYear;
    @Column(name = "mileage")
    private Integer mileage;
    @Column(name = "color")
    private String color;
    @Column(name = "engineType")
    private String engineType;
    @Column(name = "consumption")
    private Double consumption;
    @Column(name = "capacity")
    private Double capacity;
    @Column(name = "engineTaxCoefficient")
    private Double engineTaxCoefficient;
}
