package by.incubator.application.entity;

import by.incubator.application.infrastructure.orm.annotations.Column;
import by.incubator.application.infrastructure.orm.annotations.ID;
import by.incubator.application.infrastructure.orm.annotations.Table;
import lombok.*;

@Table(name = "types")
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    @ID
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "taxCoefficient")
    private Double taxCoefficient;
}
