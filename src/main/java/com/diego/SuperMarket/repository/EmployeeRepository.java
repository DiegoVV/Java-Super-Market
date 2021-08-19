package com.diego.SuperMarket.repository;

import com.diego.SuperMarket.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}