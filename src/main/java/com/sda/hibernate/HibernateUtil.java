package com.sda.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
            SESSION_FACTORY = metadata.buildSessionFactory();

        } catch (Exception exp) {
            System.out.println("Initial session factory creation failed");
            exp.printStackTrace();
            throw new ExceptionInInitializerError(exp);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
