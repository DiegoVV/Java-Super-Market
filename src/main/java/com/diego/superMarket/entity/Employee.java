package com.diego.superMarket.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="EMPLOYEE")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id")
    private Long id;
    private Float salary;
    @Column(unique = true)
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role; // Employee role, either cashier or admin

    public Employee(Float salary, String name, String password, Role role) {
        this.salary = salary;
        this.login = name;
        this.password = password;
        this.role = role;
    }

    public Employee(String name, String password) {
        this.login = name;
        this.password = password;
    }
}
