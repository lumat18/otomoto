package com.sda.respository;

import com.sda.hibernate.HibernateUtil;
import com.sda.model.Advert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertRepository {

    private static AdvertRepository advertRepository;
    private SessionFactory sessionFactory;

    public static AdvertRepository aAdvertRepository() {
        if (advertRepository == null) {
            advertRepository = new AdvertRepository(HibernateUtil.getSessionFactory());
        }
        return advertRepository;
    }

    public boolean save(Advert advert) {
        if (advert == null) {
            return false;
        }

        Session session = sessionFactory.openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            session.persist(advert);
            transaction.commit();
            log.info("Advert added to database");
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Failed to save advert to database");
        }
        return true;
    }

    public List<Advert> findAll() {
        Session session = sessionFactory.openSession();
        List<Advert> adverts = new ArrayList<>();
        try (session) {
            Transaction transaction = session.beginTransaction();
            adverts = session.createQuery("from adverts").getResultList();
            transaction.commit();
            log.info("Advert list loaded successfully");
        } catch (Exception e) {
            log.warn("Error when loading all adverts from database");
            e.printStackTrace();
        }
        return adverts;
    }

    public List<Advert> findAdvertsByLogin(String login) {
        Session session = sessionFactory.openSession();
        List<Advert> adverts = new ArrayList<>();
        try (session) {
            Transaction transaction = session.beginTransaction();
            adverts = session.createQuery("from adverts where userLogin = :login")
                    .setParameter("login", login)
                    .getResultList();
            transaction.commit();
            log.info("Advert list loaded successfully");
        } catch (Exception e) {
            log.warn("Error when loading all adverts from database");
            e.printStackTrace();
        }
        return adverts;
    }
}
