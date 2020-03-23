package ru.site.service;
import java.util.ArrayList;
import java.util.List;

import ru.site.model.Employee;
import ru.site.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getStaff() {
        List<Employee> staff = new ArrayList<Employee>();
        employeeRepository.findAll().forEach(employee -> staff.add(employee));
        return staff;
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public void saveOrUpdate(Employee employee) {
    	employeeRepository.save(employee);
    }

    public void delete(Long id) {
    	employeeRepository.deleteById(id);
    }

}