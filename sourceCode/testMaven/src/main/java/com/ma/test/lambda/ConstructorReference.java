package com.ma.test.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConstructorReference {
    public static void main(String[] args) {
        ConstructorReferencce();
    }

    private static void ConstructorReferencce() {
        ArrayList<String> names = new ArrayList<>();
        Stream<Person> stream = names.stream().map(Person::new);
        List<Person> people = stream.collect(Collectors.toList());
    }
}
