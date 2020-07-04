package com.sda.exercises.java8;

public class Employee {

    private String firstName;
    private String lastName;

    private Wallet wallet;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wallet = new Wallet();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public class Wallet {

        Employee owner;

        public Wallet() {
            this.owner = Employee.this;
        }
    }
}
