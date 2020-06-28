package com.sda.exercises.comparators;

import java.util.Comparator;

public class StringByLengthComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int lenthsComparison = Integer.compare(o1.length(), o2.length());

        if (lenthsComparison != 0) {
            return lenthsComparison;
        }

        return o1.compareTo(o2);
    }
}
