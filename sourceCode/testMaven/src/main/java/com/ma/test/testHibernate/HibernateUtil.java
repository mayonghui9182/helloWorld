package com.ma.test.testHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessions;
	static {
		Configuration cfg = new Configuration();
		cfg=cfg.configure();
		SessionFactory sessions=cfg.buildSessionFactory();
		Session session = sessions.openSession();
		session.close();
	}
	private static Session getSessionfactory() {
		return sessions.openSession();
	}	
	
}
