package by.koronatech.office.core.service;

import by.koronatech.office.api.dto.department.GetDepartmentDTO;
import by.koronatech.office.core.entity.Department;

import java.util.List;

public interface DepartmentService {
    public List<GetDepartmentDTO> getAll();
    public Department findDepartmentById(Long id);
    public Department findDepartmentByName(String name);
}
