package by.incubator.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VehicleTypeDto {
    private long typeId;
    private String name;
    private double taxCoefficient;
}
