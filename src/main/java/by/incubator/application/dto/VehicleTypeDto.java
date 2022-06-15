package by.incubator.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VehicleTypeDto {
    private int typeId;
    private String name;
    private double taxCoefficient;
}
