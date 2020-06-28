package com.sda.exercises.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Atomics {
    private static int nonAtomic = 0;
    private static int iterationCount = 1000000;

    private static synchronized void nonAtomicInc() {
        nonAtomic++;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        counter();
    }

    private static void counter() throws InterruptedException, ExecutionException {
        Random r = new Random();
        // dwa wątki próbują zwiększyć licznik 1000000 razy, jeśli nie użyją metody sychronized, nie doliczą do 2000000 :)
        List<Callable<Integer>> actions = Arrays.asList(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for (int i = 0; i < iterationCount; i++) {
                    nonAtomicInc();
                }
                return nonAtomic;
            }
        }, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for (int i = 0; i < iterationCount; i++) {
                    nonAtomicInc();
                }
                return nonAtomic;
            }
        });

        // jeśli stworzymy Executors.newSingleThreadPool(), zadania będą się wykonywały jedno za drugim i nie będzie problemów z współbieżnością
        ExecutorService atomic = Executors.newFixedThreadPool(5);

        // invokeAll czeka aż wszystkie zadania się wykonają zanim zwróci listę wyników
        // List<Future<Integer>> results = atomic.invokeAll(actions);
        List<Future<Integer>> results = new ArrayList<>();

        // jeśli ręcznie uruchomimy każde zadanie, program nie czeka aż się zakończą
        for (Callable<Integer> action : actions) {
            results.add(atomic.submit(action));
        }

        // możemy ręcznie wymusić czekanie na wynik z zadania asynchronicznego - jeśli tego nie zrobimy zmienna może być odczytana zanim wątki zwiększą jej wartość
        for (Future<Integer> result : results) {
            result.get();
        }

        System.out.println("final result: " + nonAtomic);

        // executor service trzeba zamknąć, żeby program zwyczajnie zakończył działanie
        atomic.shutdown();
    }
}
