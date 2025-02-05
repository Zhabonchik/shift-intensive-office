package by.koronatech.office.core.service.impl;

import by.koronatech.office.api.dto.department.GetDepartmentDTO;
import by.koronatech.office.core.entity.Department;
import by.koronatech.office.core.exception.NoSuchDepartmentException;
import by.koronatech.office.core.mapper.department.GetDepartmentMapper;
import by.koronatech.office.core.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final GetDepartmentMapper getDepartmentMapper;
    private final List<Department> departments = Arrays.asList(
            Department.builder().id(1L).name("Бухгалтерия").build(),
            Department.builder().id(2L).name("Отдел IT").build(),
            Department.builder().id(3L).name("Отдел продаж").build(),
            Department.builder().id(4L).name("Отдел кадров").build());

    @Override
    public List<GetDepartmentDTO> getAll() {
        return departments.stream().map(getDepartmentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Department findDepartmentById(Long id) throws NoSuchDepartmentException {
        return departments.stream()
                .filter(dep -> dep.getId().equals(id)).findFirst()
                .orElseThrow(() -> new NoSuchDepartmentException("Department doesn't exist"));
    }

    public Department findDepartmentByName(String name) throws NoSuchDepartmentException {
        return departments.stream()
                .filter(dep -> dep.getName().equals(name)).findFirst()
                .orElseThrow(() -> new NoSuchDepartmentException("Department doesn't exist"));
    }
}
