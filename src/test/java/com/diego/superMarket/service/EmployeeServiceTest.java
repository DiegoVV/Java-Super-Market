package com.diego.superMarket.service;

import com.diego.superMarket.entity.Employee;
import com.diego.superMarket.entity.Role;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService.deleteEmployee();
        System.out.println("--- Repository cleaned before test ---");
    }

    @Test
    @DisplayName("Get all employees")
    void getEmployee() {

        Employee employee = employeeService.addEmployee(new Employee(4000F,"John","johnnyboy", Role.CASHIER));
        Employee employee2 = employeeService.addEmployee(new Employee(5000F,"Mary","littlelamb", Role.CASHIER));
        Employee employee3 = employeeService.addEmployee(new Employee(10000F,"Karen","lovedogs", Role.ADMIN));

        List employeeList = new ArrayList<Employee>();
        employeeList.add(employee);
        employeeList.add(employee2);
        employeeList.add(employee3);

        Assertions.assertFalse(employeeService.getEmployee().isEmpty(), "No products were added to the repository");
        Assertions.assertEquals(employeeList, employeeService.getEmployee(), "Returned something other than the 3 mocked products");
    }

    @Test
    @DisplayName("Get a single employee")
    void getSingleEmployee() {

        Employee employee = employeeService.addEmployee(new Employee(4000F,"John","johnnyboy", Role.CASHIER));
        Employee employee2 = employeeService.addEmployee(new Employee(5000F,"Mary","littlelamb", Role.CASHIER));

        Assertions.assertEquals("John", employeeService.getEmployee(employee.getId()).getLogin());
        Assertions.assertEquals("johnnyboy", employeeService.getEmployee(employee.getId()).getPassword());
        Assertions.assertEquals(4000F, employeeService.getEmployee(employee.getId()).getSalary());
        Assertions.assertEquals(Role.CASHIER, employeeService.getEmployee(employee.getId()).getRole());

        Assertions.assertEquals("Mary", employeeService.getEmployee(employee2.getId()).getLogin());
        Assertions.assertEquals("littlelamb", employeeService.getEmployee(employee2.getId()).getPassword());
        Assertions.assertEquals(5000F, employeeService.getEmployee(employee2.getId()).getSalary());
        Assertions.assertEquals(Role.CASHIER, employeeService.getEmployee(employee2.getId()).getRole());
    }

    @Test
    @DisplayName("Add an employee to the repository")
    void addEmployee() {

        Assertions.assertTrue(employeeService.getEmployee().isEmpty(), "The repository was not empty");

        Employee employee = employeeService.addEmployee(new Employee(4000F,"John","johnnyboy", Role.CASHIER));

        Assertions.assertFalse(employeeService.getEmployee().isEmpty(), "The history was not added to the repository");
        Assertions.assertEquals("John", employeeService.getEmployee(employee.getId()).getLogin());
        Assertions.assertEquals("johnnyboy", employeeService.getEmployee(employee.getId()).getPassword());
        Assertions.assertEquals(4000F, employeeService.getEmployee(employee.getId()).getSalary());
        Assertions.assertEquals(Role.CASHIER, employeeService.getEmployee(employee.getId()).getRole());
    }

    @Test
    @DisplayName("Update an existing employee information")
    void updateEmployee() {
        Employee employee = new Employee(4000F,"John","johnnyboy", Role.CASHIER);
        Employee employee2 = new Employee(5000F,"Mary","littlelamb", Role.CASHIER);

        Long id1 = employeeService.addEmployee(employee).getId();

        Assertions.assertEquals("John", employeeService.getEmployee(id1).getLogin());
        Assertions.assertEquals("johnnyboy", employeeService.getEmployee(id1).getPassword());
        Assertions.assertEquals(4000F, employeeService.getEmployee(id1).getSalary());
        Assertions.assertEquals(Role.CASHIER, employeeService.getEmployee(id1).getRole());

        Employee employee3 = employeeService.updateEmployee(id1, employee2);

        Assertions.assertEquals("Mary", employeeService.getEmployee(id1).getLogin());
        Assertions.assertEquals("littlelamb", employeeService.getEmployee(id1).getPassword());
        Assertions.assertEquals(5000F, employeeService.getEmployee(id1).getSalary());
        Assertions.assertEquals(Role.CASHIER, employeeService.getEmployee(id1).getRole());
    }

    @Test
    @DisplayName("Delete a specific employee")
    void deleteEmployee() {
        Employee employee = employeeService.addEmployee(new Employee(4000F,"John","johnnyboy", Role.CASHIER));

        Assertions.assertFalse(employeeService.getEmployee().isEmpty(), "The product was not added to the repository");
        employeeService.deleteEmployee(employee.getId());
        Assertions.assertTrue(employeeService.getEmployee().isEmpty(), "The product was not deleted or there were other products in the repository");
    }

    @AfterEach
    void tearDown() {
        employeeService.deleteEmployee();
        System.out.println("--- Test finished ---");
        employeeService.addEmployee(new Employee(10000F,"admin","admin", Role.ADMIN));
    }
}