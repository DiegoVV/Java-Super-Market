package com.diego.SuperMarket.service.impl;

import com.diego.SuperMarket.entity.Employee;
import com.diego.SuperMarket.repository.EmployeeRepository;
import com.diego.SuperMarket.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        return employee.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee updateEmployee = getEmployee(id);

        updateEmployee.setName(employee.getName());
        updateEmployee.setPassword(employee.getPassword());
        updateEmployee.setRole(employee.getRole());
        updateEmployee.setSalary(employee.getSalary());

        return addEmployee(updateEmployee);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) {

        Employee employee = getEmployee(id);

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put(String.format("History of id %d deleted", id), Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Override
    public void deleteEmployee() {
        employeeRepository.deleteAll();
    }
}
