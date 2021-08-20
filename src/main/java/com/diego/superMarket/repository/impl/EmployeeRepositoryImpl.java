//package com.diego.superMarket.repository.impl;
//
//import com.diego.superMarket.entity.Employee;
//import com.diego.superMarket.repository.EmployeeRepositoryCustom;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@Transactional(readOnly = true)
//public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Override
//    public Optional<Employee> findByLogin(String login) {
//        Query query = entityManager.createNativeQuery("SELECT * FROM employee as em WHERE em.login LIKE ?", Employee.class);
//        query.setParameter(1, login + "%");
//
//        List found = query.getResultList();
//
//
//        if(found.size() > 0){
//            Employee employee = (Employee) found.get(0);
//            return Optional.ofNullable(employee);
//        } else {
//            return null;
//        }
//    }
//}
