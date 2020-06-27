package com.sda.exercises;

public class Point<T extends Comparable> {
    // klasa Point, która może być punktem na układzie współrzędnych
    // ale współrzędne mogą być Integer, Double, albo w zasadzie czymkolwiek, co da się porównać
    // T extends Comparable
    private T x;
    private T y;

    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public String toString() {
        return String.format("P(%s, %s)", x, y);
    }
}
