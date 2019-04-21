package com.ma.test.generics;

import com.ma.test.Class.inherit.SubClass;
import com.ma.test.Class.inherit.SupClass;

import java.util.ArrayList;
import java.util.Date;

/**
 * @version 1.00 2004-05-10
 * @author Cay Horstmann
 */
public class Pair<T> 
{
   private T first;
   private T second;

   public Pair() { first = null; second = null; }
   public Pair(T first, T second) { this.first = first;  this.second = second; }

   public T getFirst() { return first; }
   public T getSecond() { return second; }

   public void setFirst(T newValue) { first = newValue; }
   public void setSecond(T newValue) { second = newValue; }

   public static void main(String[] args) {
      Pair<SubClass> stringPair = new Pair<>();
      Pair<SupClass> datePair = new Pair<>();
      ArrayList<SubClass> subList=new ArrayList<SubClass>();
      ArrayList<SupClass> supList=new ArrayList<SupClass>();
      ArrayList<String> srtList=new ArrayList<String>();
//        subList=srtList;
//        subList=supList;
//        supList=subList;
//        supList=srtList;
      //datePair=stringPair;
   }
}
