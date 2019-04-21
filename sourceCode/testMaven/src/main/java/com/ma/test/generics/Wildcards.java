package com.ma.test.generics;

import com.ma.test.coutURL.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 参考：https://www.cnblogs.com/drizzlewithwind/p/6101081.html
 */
public class Wildcards {
    public static void main(String[] args) {
    }

    private static void TestTypeOfUnknown()

    {
//        ArrayList<?> c = new ArrayList<String>();
//        String o = c.get(0);
//        c.add(new Object()); // Compile time error
    }

    private static void test() {
//        List<List> ll=new ArrayList();
//        testArrayGeneric(ll);//compile time error
    }

    private static void testArrayGeneric(List<Collection> ll) {

    }

    static <T> void fromArrayToCollectionTest(T[] a, Collection<T> c) {
        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<Object>();

        // T inferred to be Object
        fromArrayToCollection(oa, co);

        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<String>();

        // T inferred to be String
        fromArrayToCollection(sa, cs);

        // T inferred to be Object
        fromArrayToCollection(sa, co);

        Integer[] ia = new Integer[100];
        Float[] fa = new Float[100];
        Number[] na = new Number[100];
        Collection<Number> cn = new ArrayList<Number>();

        // T inferred to be Number
        fromArrayToCollection(ia, cn);

        // T inferred to be Number
        fromArrayToCollection(fa, cn);

        // T inferred to be Number
        fromArrayToCollection(na, cn);

        // T inferred to be Object
        fromArrayToCollection(na, co);

        // compile-time error
        //fromArrayToCollection(na, cs);
    }

    static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o); // Correct
        }
    }
    void test1(){
        List<String>[] lsa = null;//new List<String>[10]
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        // Unsound, but passes run time store check
        oa[1] = li;

        // Run-time error: ClassCastException.
        String s = lsa[1].get(0);
    }
}
class MyString implements Comparable<String> {
    @Override
    public int compareTo(String str) {
        return 0;
    }
}