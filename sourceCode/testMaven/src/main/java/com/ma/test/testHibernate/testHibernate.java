package com.ma.test.testHibernate;

import java.util.LinkedHashMap;
import org.apache.xmlbeans.impl.jam.internal.elements.VoidClassImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.cfg.Configuration;

public class testHibernate {
	public static void main(String[] args) {
		SessionFactory SessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = SessionFactory.openSession();
		TestTab testTab = new TestTab();
		testTab.setField1("field1");
		testTab.setField2("field2");
		testTab.setField3("field3");
		session.save(testTab);//find(TestTab.class);
		session.close();
		
	}
}
