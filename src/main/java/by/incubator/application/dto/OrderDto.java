package by.incubator.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDto {
    private long id;
    private long vehicleId;
    private String defect;
    private int breakingAmount;
}
