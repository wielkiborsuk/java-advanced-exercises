package com.sda.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileExercies {

    public static void main(String[] args) throws Exception {
        // plik znajduje się w projekcie, w folderze src/main/resources
        Path filePath = Paths.get("src/main/resources/words.txt");

        List<String> words = new ArrayList<>();
        // odczytujemy wszystkie linijki z pliku
        for (String line : Files.readAllLines(filePath)) {
            // każdą linijkę można podzielić na słowa - "\\s" oznacza dowolny biały znak, można użyć innych metod :)
            for (String word : line.split("\\s")) {
                words.add(word);
            }
        }

        // TODO - mając listę wszystkich słów z pliku, znajdujemy najdłuższy wyraz
    }
}
