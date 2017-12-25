package com.ma.test.testHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessions;
	private static ThreadLocal<Session> sessionLocal=new ThreadLocal<Session>();
	static {
		init();
	}
	@SuppressWarnings("unused")
	private static SessionFactory getSessionfactory() {
		if(sessions==null){
			synchronized (HibernateUtil.class) {
				if(sessions==null){
					init();
				}
			}
		}
		return sessions;
	}
	private static void init(){
		Configuration cfg = new Configuration().configure();
		sessions=cfg.buildSessionFactory();
	}
	@SuppressWarnings("unused")
	private static Session getSession(){
		Session session=sessionLocal.get();
		if(session==null){
			session=sessions.openSession();
			sessionLocal.set(session);
		}
		return session;
	}
	@SuppressWarnings("unused")
	private static void closeSession(){
		Session session=sessionLocal.get();
		if(session!=null){
			session.close();
		}
		sessionLocal.set(null);
	}
		
	
}
