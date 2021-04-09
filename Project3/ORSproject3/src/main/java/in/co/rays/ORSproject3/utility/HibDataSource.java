package in.co.rays.ORSproject3.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate DataSource for Data Connection Pool
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 *
 */

public  final class HibDataSource {

    private static SessionFactory sessionFactory = null;

    private HibDataSource(){
    	
    }
    
    /**
     * Get the instance of Session Factory
     *
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure()
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * Get Session by SessionFactory
     *
     * @return session
     */
    public static Session getSession() {
        Session session = getSessionFactory().openSession();
        return session;
    }

    /**
     * Get Session by SessionFactory
     *
     * @return session
     */
    public static void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }

}