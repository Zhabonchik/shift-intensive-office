package by.koronatech.office.core.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Employee {
    private Long id;
    private String name;
    private Double salary;
    private String department;
    private Boolean manager;
}
