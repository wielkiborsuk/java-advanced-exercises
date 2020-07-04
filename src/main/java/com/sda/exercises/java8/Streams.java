package com.sda.exercises.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    // krótki przykład przetwarzania kolekcji w stumieniu
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        // tworzymy listę 1000 kolejnych liczb
        for (int i = 0; i<1000; i++) {
            numbers.add(i);
        }

        // tworzymy strumień z kolekcji
        List<String> numberString = numbers.stream()
                // pozostawiamy w strumieniu nieparzyste liczby
                .filter((n) -> n%2 == 1)
                // każdy pozostały element podnosimy do kwadratu
                .map((n) -> n*n)
                // każdy element strumienia zmieniamy na napis (przy użyciu wskaźnika na funkcję w klasie String)
                // i tak wiadomo, że jest tylko jeden argument, który chcielibyśmy tam przekazać, czyli nasz aktualny element strumienia
                .map(String::valueOf)
                // mając strumień Stringów, możemy pozostawić w strumieniu tylko napisy zawierające cyfrę 9
                .filter(n -> n.contains("9"))
                // kończąc przetwarzanie prosimy, żeby elementy strumienia były zwrócone w formie listy
                // ponieważ po drodze zmieniliśmy typ elementu strumienia na String
                // wynikowa wartość to List<String>
                .collect(Collectors.toList());

        System.out.println(numberString);
    }
}
