package by.koronatech.office.core.service.impl;

import by.koronatech.office.api.dto.employee.CreateEmployeeDTO;
import by.koronatech.office.api.dto.employee.GetEmployeeDTO;
import by.koronatech.office.core.entity.Department;
import by.koronatech.office.core.entity.Employee;
import by.koronatech.office.core.exception.NoSuchDepartmentException;
import by.koronatech.office.core.exception.NoSuchEmployeeException;
import by.koronatech.office.core.mapper.employee.CreateEmployeeMapper;
import by.koronatech.office.core.mapper.employee.GetEmployeeMapper;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.core.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final GetEmployeeMapper getEmployeeMapper;
    private final CreateEmployeeMapper createEmployeeMapper;
    private final List<Employee> employees = new ArrayList<>(Arrays.asList(
            Employee.builder().id(1L).name("Иван").salary(1500.0).department("Бухгалтерия").manager(Boolean.TRUE).build(),
            Employee.builder().id(2L).name("Максим").salary(1400.0).department("Отдел IT").manager(Boolean.TRUE).build(),
            Employee.builder().id(3L).name("Анна").salary(1450.0).department("Отдел продаж").manager(Boolean.TRUE).build(),
            Employee.builder().id(4L).name("Наталья").salary(1500.0).department("Отдел кадров").manager(Boolean.TRUE).build(),
            Employee.builder().id(5L).name("Дмитрий").salary(1300.0).department("Отдел IT").manager(Boolean.FALSE).build(),
            Employee.builder().id(6L).name("Олеся").salary(1320.0).department("Бухгалтерия").manager(Boolean.FALSE).build()
    ));
    private static Long ID = 6L;
    private final DepartmentService departmentService;

    @Override
    public List<GetEmployeeDTO> getAllByDepartmentName(String departmentIName) {
        return employees.stream()
                .filter(employee -> employee.getDepartment().equals(departmentIName))
                .map(getEmployeeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public GetEmployeeDTO create(CreateEmployeeDTO createEmployeeDTO) throws NoSuchDepartmentException {
        Department department = departmentService.findDepartmentByName(createEmployeeDTO.getDepartment());
        Employee employee = createEmployeeMapper.toEntity(createEmployeeDTO);
        employee.setId(++ID);
        employees.add(employee);
        return getEmployeeMapper.toDTO(employee);
    }

    @Override
    public GetEmployeeDTO makeDepartmentManager(Long employeeId) throws NoSuchEmployeeException {
        Employee employee = findById(employeeId);
        employee.setManager(Boolean.TRUE);
        return getEmployeeMapper.toDTO(employee);
    }

    @Override
    public GetEmployeeDTO update(Long employeeId, CreateEmployeeDTO createEmployeeDTO) throws NoSuchEmployeeException {
        Employee employee = findById(employeeId);
        // use createEmployeeMapper?
        if(createEmployeeDTO.getName() != null){
            employee.setName(createEmployeeDTO.getName());
        }
        if(createEmployeeDTO.getSalary() != null){
            employee.setSalary(createEmployeeDTO.getSalary());
        }
        if(createEmployeeDTO.getDepartment() != null){
            employee.setDepartment(createEmployeeDTO.getDepartment());
        }
        if(createEmployeeDTO.getManager() != null){
            employee.setManager(createEmployeeDTO.getManager());
        }
        return getEmployeeMapper.toDTO(employee);
    }

    @Override
    public void delete(Long employeeId) throws NoSuchEmployeeException{
        Employee employee = findById(employeeId);
        employees.remove(employee);
    }

    private Employee findById(Long employeeId) throws NoSuchEmployeeException {
        return employees.stream()
                .filter(e -> e.getId().equals(employeeId)).findFirst()
                .orElseThrow(() -> new NoSuchEmployeeException("Employee doesn't exist"));
    }
}
