package com.ma.test.testHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessions;
	static {
		sessions=new Configuration().buildSessionFactory();
	}
	private static Session getSessionfactory() {
		return sessions.openSession();
	}
	
}
