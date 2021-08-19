package com.diego.SuperMarket.service;

import com.diego.SuperMarket.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> getEmployee();

    Employee getEmployee(Long id);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id);

    void deleteEmployee();
}
