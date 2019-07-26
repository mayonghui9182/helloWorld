package com.ma.pattern.TheBuildPattern;

import java.util.Comparator;

import static com.ma.pattern.TheBuildPattern.NyPizza.Size.SMALL;
import static com.ma.pattern.TheBuildPattern.Pizza.Topping.HAM;
import static com.ma.pattern.TheBuildPattern.Pizza.Topping.ONION;
import static com.ma.pattern.TheBuildPattern.Pizza.Topping.SAUSAGE;

public class test {
    public static void main(String[] args) {
        NyPizza.Builder builder = new NyPizza.Builder(SMALL);
        builder.addTopping(SAUSAGE);
        builder.addTopping(ONION);
        NyPizza pizza = builder.build();
        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM).sauceInside().build();

        Comparator
    }
}
