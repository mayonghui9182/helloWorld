package com.ma.test.testHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class testHibernate {
	public static void main(String[] args) {
		SessionFactory buildSessionFactory = new Configuration().buildSessionFactory();
		Session Session = buildSessionFactory.openSession();
		Session.saveOrUpdate(new Object());
	}
}
