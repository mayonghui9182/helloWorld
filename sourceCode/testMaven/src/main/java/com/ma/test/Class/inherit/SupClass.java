package com.ma.test.Class.inherit;

public class SupClass {
    private String Coller;

    @Override
    public boolean equals(Object obj) {
        SupClass obj1 = (SupClass) obj;
        boolean b = obj1.Coller == Coller;
        return super.equals(obj);
    }

    private void supPrivateMethod() {
        System.out.println("supPrivateMethod");
    }

}
