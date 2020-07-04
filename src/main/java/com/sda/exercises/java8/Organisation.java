package com.sda.exercises.java8;

import java.util.List;

public class Organisation {
    private List<Department> departments;

    public Organisation(List<Department> departments) {
        this.departments = departments;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
