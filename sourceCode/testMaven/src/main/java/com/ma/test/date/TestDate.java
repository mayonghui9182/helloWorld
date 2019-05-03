package com.ma.test.date;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

public class TestDate {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date(Long.parseLong("1556702238625")));
    }
}

class MyDateEditor extends DateFormat {
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return new StringBuffer().append(date.getTime());
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        new Date();
        return null;
    }
}