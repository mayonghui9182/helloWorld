package com.ma.test.Collection;

import java.util.ArrayList;

public class TestCollection {
    public static void main(String[] args) {
        TestToArray();
    }

    public static void TestToArray() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("ma");
        strings.add("yong");
        strings.add("hui");
        strings.toArray(new String[5]);
    }
}
