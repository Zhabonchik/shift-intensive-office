package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.department.GetDepartmentDTO;
import by.koronatech.office.api.dto.employee.GetEmployeeDTO;
import by.koronatech.office.core.entity.Department;
import by.koronatech.office.core.exception.ErrorResponse;
import by.koronatech.office.core.exception.NoSuchDepartmentException;
import by.koronatech.office.core.exception.NoSuchEmployeeException;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.core.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    /*Получение всех отделов*/
    @GetMapping
    public List<GetDepartmentDTO> getDepartments() {
        return departmentService.getAll();
    }

    /*Получение всех сотрудников отдела*/
    @GetMapping("/{id}/employees")
    public List<GetEmployeeDTO> getEmployees(@PathVariable Long id) {
        Department department = departmentService.findDepartmentById(id);
        return employeeService.getAllByDepartmentName(department.getName());
    }

    @ExceptionHandler(value = NoSuchEmployeeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomerAlreadyExistsException(NoSuchEmployeeException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = NoSuchDepartmentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomerAlreadyExistsException(NoSuchDepartmentException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

}
