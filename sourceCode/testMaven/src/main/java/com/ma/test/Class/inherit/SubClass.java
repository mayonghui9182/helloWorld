package com.ma.test.Class.inherit;

public class SubClass extends SupClass{
    private void supPrivateMethod() {
        System.out.println("supPrivateMethod");
    }
    @Override
    public void selfMethod(){
        System.out.println("selfMethodOfSub");
    }
}
