package com.sda.exercises;

import java.util.*;

public class Collections {
    public static void main(String[] args) {
        Integer[] numbers = generate100RandomNumbers();

        List<Integer> unique = searchForUniqueValues(numbers);
//        List<Integer> unique = getUniqueValues(numbers); // alternatywne podejście

        List<Integer> duplicate = getDuplicatedValues(numbers);

        System.out.println(unique);
        System.out.println(duplicate);
    }

    private static Integer[] generate100RandomNumbers() {
        Integer[] numbers = new Integer[100];
        Random r = new Random();

        for (int i = 0; i < 100; i++) {
            numbers[i] = r.nextInt(50);
            // r.nextInt - zwraca liczbę 0-49, ograniczenie od góry jest wyłączne
            // (int)(Math.random()*50) - da podobny wynik
        }
        return numbers;
    }

    private static List<Integer> searchForUniqueValues(Integer[] numbers) {
        List<Integer> unique = new ArrayList<>();
        for (Integer n : numbers) {
            // dodajemy element do listy, tylko jeśli jeszcze nie ma go w liście
            if (!unique.contains(n)) {
                unique.add(n);
            }
        }
        // dostaniemy listę pierwszych wystąpień każdej wartości z zachowaną kolejnością
        return unique;
    }

    private static List<Integer> getUniqueValues(Integer[] numbers) {
        Set<Integer> unique = new HashSet<>();
        for (Integer n : numbers) {
            // dodajemy element do zbioru, zbiór dba o unikalność wyników
            unique.add(n);
        }
        // dostaniemy unikalną listę wartości, kolejność zostanie zmieniona
        return new ArrayList<>(unique);
    }

    private static List<Integer> getDuplicatedValues(Integer[] numbers) {
        List<Integer> duplicates = new ArrayList<>();
        List<Integer> uniques = getUniqueValues(numbers);

        // tworzymy listę wszystkich wartości z powtórzeniami
        for (Integer number : numbers) {
            duplicates.add(number);
        }

        // dla każdej unikalnej wartości usuwamy pierwsze wystąpienie w liście
        for (Integer uniqueN : uniques) {
            duplicates.remove(uniqueN);
        }

        // w liście zostają tylko wartości, które miały powtórzenia
        // używamy Seta, żeby dostać zbiór wartości bez dodatkowych duplikatów
        duplicates = new ArrayList<>(new HashSet<>(duplicates));
        return duplicates;
    }
}
