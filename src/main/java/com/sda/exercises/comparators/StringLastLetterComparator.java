package com.sda.exercises.comparators;

import java.util.Comparator;

public class StringLastLetterComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        // przed: Czarek
        String reverseO1 = new StringBuilder(o1).reverse().toString();
        // po: kerazC
        String reverseO2 = new StringBuilder(o2).reverse().toString();
        return reverseO1.compareTo(reverseO2);
    }
}
