package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.employee.CreateEmployeeDTO;
import by.koronatech.office.api.dto.employee.GetEmployeeDTO;

import java.util.List;

public interface EmployeeService {
    public List<GetEmployeeDTO> getAllByDepartmentName(String departmentName);

    public GetEmployeeDTO create(CreateEmployeeDTO createEmployeeDTO);

    public GetEmployeeDTO makeDepartmentManager(Long employeeId);

    public GetEmployeeDTO update(Long employeeId, CreateEmployeeDTO createEmployeeDTO);

    public void delete(Long employeeId);
}
