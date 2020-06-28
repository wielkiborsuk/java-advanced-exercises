package com.sda.exercises.threads;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class ParallelSorting {
    // algorytm sortowania bąbelkowego - przykład z internetu. sortowanie bąbelkowe nie jest optymalne
    static int[] bubbleSorted(int[] sorted) {
        int n = sorted.length;
        int temp = 0;

        for(int i=0; i < n; i++) {
            for(int j=1; j < (n-i); j++) {
                if(sorted[j-1] > sorted[j]) {
                    //swap elements
                    temp = sorted[j-1];
                    sorted[j-1] = sorted[j];
                    sorted[j] = temp;
                }

            }
        }
        return sorted;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random r = new Random();
        int[] randoms = new int[10000];
        for (int i=0; i<randoms.length; i++) {
            randoms[i] = r.nextInt(10000);
        }

        // tworzymy dwie kopie tablicy, żeby każdy algorytm mógł zmienić swoją kopię i żeby nie wchodziły sobie w drogę
        final int[] randoms1 = Arrays.copyOf(randoms, randoms.length);
        final int[] randoms2 = Arrays.copyOf(randoms, randoms.length);

        // prk
        Callable<Integer> bubbly = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                long start = System.currentTimeMillis();
                System.out.println("starting bubble sort");
                bubbleSorted(randoms1);
                System.out.println("finished bubble sort!");
                return (int)(System.currentTimeMillis() - start);
            }
        };
        Callable<Integer> builtin = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                long start = System.currentTimeMillis();
                System.out.println("starting builtin sort");
                Arrays.sort(randoms2);
                System.out.println("finished builtin sort!");
                return (int)(System.currentTimeMillis() - start);
            }
        };

        // tworzymy executor service, żeby unaocznić kolejność kończenia zadań przez wiadomości w logach, nie tylko w czasie wykonania
        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> bubbleResult = service.submit(bubbly);
        Future<Integer> builtinResult = service.submit(builtin);

        // zwracamy czas wykonania każdego wątku, możemy je na koniec porównać
        if (bubbleResult.get() > builtinResult.get()) {
            System.out.println(String.format("Bubble sort %d was slower than quicksort %d", bubbleResult.get(), builtinResult.get()));
        } else {
            System.out.println(String.format("Bubble sort %d was faster than quicksort %d", bubbleResult.get(), builtinResult.get()));
        }

        service.shutdown();
    }
}
