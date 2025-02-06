package by.koronatech.office.api.controller;

import by.koronatech.office.api.dto.employee.CreateEmployeeDTO;
import by.koronatech.office.api.dto.employee.GetEmployeeDTO;
import by.koronatech.office.core.entity.Department;
import by.koronatech.office.core.exception.NoSuchDepartmentException;
import by.koronatech.office.core.exception.NoSuchEmployeeException;
import by.koronatech.office.core.service.DepartmentService;
import by.koronatech.office.core.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import by.koronatech.office.core.exception.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    /*Добавление сотрудника в отдел*/
    @PostMapping("/add")
    public GetEmployeeDTO addToDepartment(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return employeeService.create(createEmployeeDTO);
    }

    /*Сделать сотрудника менеджером отдела*/
    @PatchMapping("/{id}/manager")
    public GetEmployeeDTO updateManager(@PathVariable Long id) {
        return employeeService.makeDepartmentManager(id);
    }

    /*Изменение существующей информации о сотруднике*/
    @PutMapping("/{id}/update")
    public GetEmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return employeeService.update(id, createEmployeeDTO);
    }

    /*Удаление существующего сотрудника из отдела*/
    @DeleteMapping("/{id}/delete")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
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
