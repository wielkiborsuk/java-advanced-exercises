package com.sda.exercises.java8;

import javax.swing.text.html.Option;
import java.util.*;

public class OptionalExample {

    public static void main(String[] args) {
        // tworzymy organizację z jednym działem i jednym pracownikiem
        Employee e = new Employee("Franek SDA", "Kimono");
//        List<Employee> employees = Collections.singletonList(e);
        List<Employee> employees = Collections.emptyList();

        List<Department> departments = Collections.singletonList(new Department(employees));

        Organisation o = new Organisation(departments);

        // nasza funkcja zadziała prawidłowo nawet jeśli którakolwiek z wartości po drodze
        // będzie null albo pustą kolekcją
        getOrganisationInternals(o);
    }

    private static void getOrganisationInternals(Organisation org) {
        String firstEmployeeFirstName = Optional.ofNullable(org)
                // udajemy jakby obiekty istniały
                .map(organisation -> organisation.getDepartments())
                // bezpiecznie wyciągamy pierwszy element listy
                .flatMap(departments -> departments.stream().findFirst())
                .map(department -> department.getEmployees())
                .flatMap(employees -> employees.stream().findFirst())
                .map(employee -> employee.getFirstName())
                // mamy okazję odrzucić wartość przekazywaną w Optional, jeśli nie spełnia jakiegoś warunku
                .filter(firstName -> firstName.contains("SDA"))
                // możemy też mapować wartości jak w strumieniach
                .map(firstName -> firstName.toUpperCase())
                // jeśli na którymkolwiek etapie wartości nie było, dostarczamy alternatywny wynik
                .orElse("");

        if (!firstEmployeeFirstName.isEmpty()) {
            System.out.println(firstEmployeeFirstName);
        } else {
            System.out.println("not enough information");
        }
    }
}
