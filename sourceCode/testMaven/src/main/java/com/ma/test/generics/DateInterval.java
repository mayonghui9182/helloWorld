package com.ma.test.generics;

import java.time.LocalDate;

public class DateInterval extends Pair<LocalDate> {
    @Override
    public void setSecond(LocalDate second) {
        if (second.compareTo(getFirst()) >= 0) {
            System.out.println("child");
            super.setSecond(second);
        }
    }

    public static void main(String[] args) {
        DateInterval dateInterval = new DateInterval();
        dateInterval.setFirst(LocalDate.now());
        Pair pair=dateInterval;
        pair.setSecond(LocalDate.now());
    }
}
