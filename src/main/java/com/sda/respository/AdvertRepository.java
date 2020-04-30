package com.sda.respository;

import com.sda.model.Advert;
import com.sda.model.User;
import com.sda.utils.HibernateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Advert> findAdvertsByLogin(Long userId) {
        Session session = sessionFactory.openSession();
        List<Advert> adverts = new ArrayList<>();
        try (session) {
            Transaction transaction = session.beginTransaction();
            adverts = session.createQuery("from adverts where author.id = :authorId")
                    .setParameter("authorId", userId)
                    .getResultList();
            transaction.commit();
            log.info("Advert list loaded successfully");
        } catch (Exception e) {
            log.warn("Error when loading all adverts from database");
            e.printStackTrace();
        }
        return adverts;
    }

    public Optional<Advert> findAdvertById(Long advertId) {
        Session session = sessionFactory.openSession();
        Optional<Advert> advertOptional = Optional.empty();
        try (session) {
            Transaction transaction = session.beginTransaction();
            advertOptional = session.createQuery("from adverts where id = : id")
                    .setParameter("id", advertId)
                    .getResultList().stream().findFirst();
            transaction.commit();
            log.info("Advert found by id: " + advertId);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Advert with id: " + advertId + " not found");
        }
        return advertOptional;
    }

    public List<Advert> findObservedByUserId(Long id) {
        Session session = sessionFactory.openSession();
        List<Advert> observed = new ArrayList<>();
        try (session){
            Transaction transaction = session.beginTransaction();
            observed = (List<Advert>) session.createQuery("select u.observed from users u where u.id = :id")
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
            log.info("Observed adverts of user with id=" + id + " loaded successfully");
        }catch (Exception e){
            log.warn("Failed to load observed adverts");
            e.printStackTrace();
        }
        return observed;
    }
}
