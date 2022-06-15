package by.incubator.application.dto;

import by.incubator.application.infrastructure.orm.annotations.Column;
import by.incubator.application.infrastructure.orm.annotations.ID;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class RentDto {
    private long carId;
    private Date date;
    private double cost;
}
