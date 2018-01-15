package com.ma.test.testHibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import org.apache.xmlbeans.impl.jam.internal.elements.VoidClassImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.cfg.Configuration;

public class testHibernate {
	public static void main(String[] args) {
		try {
			SessionFactory SessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = SessionFactory.openSession();
			Transaction Transaction = session.beginTransaction();
			TestTab testTab;
			testTab =session.get(TestTab.class, 1);
			testTab.setTid(5);
			testTab.setField1("field1");
			testTab.setField2("field2");
	 		testTab.setField3("field3");
			session.saveOrUpdate(testTab);//find(TestTab.class);
			Transaction.commit();
			session.close();
			SessionFactory.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
