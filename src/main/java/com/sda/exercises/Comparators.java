package com.sda.exercises;

import com.sda.exercises.comparators.StringByLengthComparator;
import com.sda.exercises.comparators.StringLastLetterComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Comparators {
    private static Comparator<String> byLength = new StringByLengthComparator();
    private static StringLastLetterComparator byLastLetter = new StringLastLetterComparator();

    public static void main(String[] args) {
        List<String> unsorted = Arrays.asList(new String[] {
                "Alicja", "Andrzej", "Czarek", "Damian", "Dawid", "Leszek",
                "Marcin", "Mariusz", "Michał", "Paweł", "Wojtek"
        });

        unsorted.sort(byLastLetter);
//        unsorted.sort(byLength);

        System.out.println(unsorted);
    }

}
