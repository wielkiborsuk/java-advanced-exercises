package com.sda.exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Generic {
    public static void main(String[] args) {
        Random r = new Random();

        System.out.println("===  punkty z liczbami całkowitymi ===");
        List<Point<Integer>> integerPoints = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            integerPoints.add(new Point<>(r.nextInt(30), r.nextInt(30)));
        }
        integerPoints.sort(xFirst);
        System.out.println(integerPoints);

        System.out.println("===  punkty z liczbami zmiennoprzecinkowymi ===");
        List<Point<Double>> decimalPoints = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            decimalPoints.add(new Point<>(r.nextDouble(), r.nextDouble()));
        }
        decimalPoints.sort(xFirst);
        System.out.println(decimalPoints);

        System.out.println("===  punkty z literami ===");
        List<Point<Character>> points = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            char c1 = (char)(r.nextInt(26) + 'a');
            char c2 = (char)(r.nextInt(26) + 'a');
            points.add(new Point<>(c1, c2));
        }
        points.sort(xFirst);
        System.out.println(points);
    }

    // komparator, który najpierw porównuje "współrzędną" X a dopiero później Y
    // komparator korzysta z faktu, że pola w Point zawsze są Comparable
    // więc można na nich wywoływać metodę compareTo
    static Comparator<Point<?>> xFirst = new Comparator<Point<?>>() {
        @Override
        public int compare(Point o1, Point o2) {
            int xComparison = o1.getX().compareTo(o2.getX());

            if (xComparison == 0) {
                return o1.getY().compareTo(o2.getY());
            }

            return xComparison;
        }
    };
}

