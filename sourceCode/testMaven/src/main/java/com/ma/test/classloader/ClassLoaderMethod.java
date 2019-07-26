package com.ma.test.classloader;

public class ClassLoaderMethod {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoaderMethod.class.getClassLoader();
        classLoader.loadClass("com.ma.test.classloader.LoadedOrder");

    }
}
