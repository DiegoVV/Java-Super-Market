package com.diego.superMarket.service;

import com.diego.superMarket.data.MyUserDetailsData;
import com.diego.superMarket.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws NoSuchElementException {
        Employee employee = employeeService.getEmployeeByName(userName);

        return new MyUserDetailsData(employee);
    }
}
