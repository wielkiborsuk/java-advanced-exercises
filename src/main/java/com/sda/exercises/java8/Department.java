package com.sda.exercises.java8;

import java.util.List;

public class Department {
    private List<Employee> employees;

    public Department(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
