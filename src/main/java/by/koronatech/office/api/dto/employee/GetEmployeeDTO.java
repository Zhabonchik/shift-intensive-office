package by.koronatech.office.api.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetEmployeeDTO {
    private Long id;
    private String name;
    private Double salary;
    private String department;
    private Boolean manager;
}
