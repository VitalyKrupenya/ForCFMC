package ru.site.controller;
import java.util.List;

import ru.site.model.Employee;
import ru.site.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/staff")
    private List<Employee> getAllStaff() {
        return employeeService.getStaff();
    }

    @GetMapping("/employee/{id}")
    private Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/employee/{id}")
    private void deletePerson(@PathVariable("id") Long id) {
        employeeService.delete(id);
    }

    @PostMapping("/employee")
    private Long savePersons(@RequestBody Employee employee) {
        employeeService.saveOrUpdate(employee);
        return employee.getId();
    }
}