package com.sda.exercises;

import com.sda.exercises.java8.Employee;

import java.lang.reflect.Field;

public class Reflection {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        // zabraliśmy klasie settery, nie ma możliwości zmiany imienia
        Employee employee = new Employee("Mirosław", "Kowalczyk");

        // ale możemy się dobrać do definicji klasy
        Class<Employee> employeeClass = Employee.class;

        for (Field f : employeeClass.getDeclaredFields()) {
            System.out.println(f);
        }

        // znaleźć odpowiednie pole i zmusić javę do przypisania wartości do pola prywatnego
        Field firstNameField = employeeClass.getDeclaredField("firstName");
        firstNameField.setAccessible(true);
        firstNameField.set(employee, "Krzysztof");

        // działa! :)
        System.out.println(employee.getFirstName());
    }

    // jak zepsuć integera?
    private static void meddleWithInteger() throws NoSuchFieldException, IllegalAccessException {
        Class<Integer> integerClass = Integer.class;
        Field value = integerClass.getDeclaredField("value");

        // zmieniamy wewnętrzny cache, który odpowiada za tworzenie obiektów dla małych wartości -128,127
        value.setAccessible(true);
        value.set(5, 2);
        value.set(1, 11);
        value.set(3, 9);

        // nagle przypisywanie wartości nie działa tak, jak byśmy się spodziewali
        Integer a = 1;
        Integer b = 3;

        System.out.println(a + b);
    }
}
