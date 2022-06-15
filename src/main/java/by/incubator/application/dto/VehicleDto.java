package by.incubator.application.dto;

import by.incubator.application.infrastructure.orm.annotations.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VehicleDto {
    private long id;
    private long typeId;
    private String typeName;
    private double taxCoefficient;
    private String modelName;
    private String registrationNumber;
    private int weight;
    private int manufactureYear;
    private int mileage;
    private String color;
    private String engineName;
    private double tankVolume;
    private double engineTaxCoefficient;
    private double per100km;
    private double maxKm;
    private double tax;
    private double income;
}
