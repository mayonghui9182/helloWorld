package generics;// generics/GenericHolder2.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;

public class GenericHolder2<T> {
  // public static T test;
  public T genericBound;
  private T obj;
  private ArrayList<? super Fruit> test;
  public void set(T obj) { this.obj = obj; }
  public T get() { return obj; }
  public ArrayList<? super T> get2() { return new ArrayList<>(); }
  public static void main(String[] args) {
    GenericHolder2<String> holder =
      new GenericHolder2<>();
    holder.set("Item");
    String genericBound = holder.genericBound;
    String s = holder.get();
    GenericHolder2<? super Fruit> wildcard =
            new GenericHolder2<>();
    Object object = wildcard.get();
    Object genericBound1 = wildcard.genericBound;
  }
}
