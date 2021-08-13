package com.diego.SuperMarket.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private Float salary;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role; // Employee role, either cashier or admin

    public Employee(Float salary, String name, String password, Role role) {
        this.salary = salary;
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
