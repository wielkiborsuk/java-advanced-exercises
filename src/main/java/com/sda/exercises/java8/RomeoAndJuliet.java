package com.sda.exercises.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class RomeoAndJuliet {

    public static void main(String[] args) throws IOException {
        // wczytanie zawartości pliku do listy linii
        Path romeoAndJuliet = Paths.get("src/main/resources/romeo.i.julia.txt");
        List<String> playLines = Files.readAllLines(romeoAndJuliet);

        System.out.println(findAndCount(playLines, "Tragedya w 5 Aktach"));
        System.out.println(findAndCountByStreaming(playLines, "Tragedya w 5 Aktach"));
    }

    // rozwiązanie proste, używające tylko podstawowych konstrukcji, javy
    // jak najbardziej działa, ale zachęcam, żebyście wrócili do niego za tydzień
    // i sprawdzili, czy dalej rozumiecie kod w całości :)
    private static String findAndCount(List<String>linesList,String ourString){
        for (String lines:linesList){
            if (lines.contains(ourString)){
                String[] wordsFromLines = lines.trim().split(" ");
                System.out.println(Arrays.toString(wordsFromLines));
                StringBuilder stringBuilder = new StringBuilder() ;
                for (String singleWord:wordsFromLines){
                    if (!singleWord.isEmpty()) {
                        int singleWordInt = singleWord.length();
                        stringBuilder.append("+").append(singleWordInt);
                    }
                }
                return stringBuilder.substring(1) ;
            }
        }
        return " ";
    }

    // rozwiązanie używające stream i optional - trudno rozczytać na początku, ale później jest bardziej przejrzyste
    private static String findAndCountByStreaming(List<String>linesList,String ourString) {
        // w strumieniu pozostaw tylko pasujące linijki i zwróć pierwszą (albo "" jeśli nic nie znalazłeś)
        String foundLine = linesList.stream()
                .filter(line -> line.contains(ourString))
                .findFirst().orElse("");

        String[] wordsFromLines = foundLine.split(" ");

        // linijkę podzieloną na słowa przefiltruj (odrzuć puste słowa)
        // oblicz długości słów
        // połącz liczby "+" przy pomocy specjalnego Collectora
        return Arrays.stream(wordsFromLines)
                .filter(s -> !s.isEmpty())
                .map(String::length)
                .map(String::valueOf)
                .collect(Collectors.joining("+"));
    }
}
