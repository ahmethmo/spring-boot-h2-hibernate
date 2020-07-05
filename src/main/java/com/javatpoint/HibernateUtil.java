package com.javatpoint;

import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.javatpoint.model.Bilet;
import com.javatpoint.model.Firma;
import com.javatpoint.model.HavaAlani;
import com.javatpoint.model.Rota;
import com.javatpoint.model.Ucus;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static Properties pro;
	
	static {
		FileInputStream fis;
		try {
			fis = new FileInputStream("src/main/resources/application.properties");
			pro  = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration conf = new Configuration();
			Properties p = new Properties();
			p.setProperty("hibernate.connection.driver_class",	pro.getProperty("spring.datasource.driverClassName"));
			p.setProperty("hibernate.dialect", 					pro.getProperty("spring.jpa.database-platform"));
			p.setProperty("hibernate.connection.username", 		pro.getProperty("spring.datasource.username"));
			p.setProperty("hibernate.connection.password", 		pro.getProperty("spring.datasource.password"));
			p.setProperty("hibernate.connection.url", 			pro.getProperty("spring.datasource.url"));
			p.setProperty("show_sql", 							"true");
			conf.setProperties(p);
			conf.addAnnotatedClass(Bilet.class);
			conf.addAnnotatedClass(Firma.class);
			conf.addAnnotatedClass(HavaAlani.class);
			conf.addAnnotatedClass(Rota.class);
			conf.addAnnotatedClass(Ucus.class);
			sessionFactory = conf.buildSessionFactory();
		}
		return sessionFactory;
	}
	
	public static Session openSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	public static void closeSession(Session ses) {
		if (ses != null) {
			rollBackSession(ses);
			ses.close();
			ses = null;
		}
	}
	
	public static void rollBackSession(Session ses) {
		if (ses != null) {
			if (ses.isOpen()) {
				if (ses.getTransaction().isActive()) {
					ses.getTransaction().rollback();
				}
			}
		}
	}
}
