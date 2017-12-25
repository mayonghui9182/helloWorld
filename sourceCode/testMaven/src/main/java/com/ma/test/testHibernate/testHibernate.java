package com.ma.test.testHibernate;

import java.util.LinkedHashMap;

import org.apache.xmlbeans.impl.jam.internal.elements.VoidClassImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.cfg.Configuration;

public class testHibernate {
	public static void main(String[] args) {

		final String s = new String("final 对象");
		System.out.println(s.toString());
		System.out.println(ClassLoaderServiceImpl.class.getClassLoader());
		System.out.println(ClassLoader.getSystemClassLoader());
		System.out.println(Thread.currentThread().getContextClassLoader());
	}
}
