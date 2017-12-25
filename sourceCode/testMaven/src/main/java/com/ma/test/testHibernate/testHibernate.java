package com.ma.test.testHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.cfg.Configuration;

public class testHibernate {
	public static void main(String[] args) {
		ClassLoader cl=ClassLoaderServiceImpl.class.getClassLoader();
		System.out.println(cl.toString());
	}
}
