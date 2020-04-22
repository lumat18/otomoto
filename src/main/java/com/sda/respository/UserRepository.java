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
public class UserRepository {

    private static UserRepository userRepository;
    private SessionFactory sessionFactory;


    public static UserRepository aUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepository(HibernateUtil.getSessionFactory());
        }
        return userRepository;
    }

    public boolean save(User user) {
        Optional<User> existingUser = findByLogin(user.getLogin());
        if (doesUserExistInDatabase(user)) {
            return false;
        }

        Session session = sessionFactory.openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            log.info("Saving " + user.getLogin() + " to database");
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean doesUserExistInDatabase(User user) {
        Optional<User> existingUser = findByLogin(user.getLogin());
        return existingUser.isPresent();
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        List<User> users = new ArrayList<>();
        try (session) {
            Transaction transaction = session.beginTransaction();
            users = (List<User>) session.createQuery("from users").getResultList();
            transaction.commit();
            log.info("User list loaded successfully");
        } catch (Exception e) {
            log.warn("Error when loading all users from database");
            e.printStackTrace();
        }
        return users;
    }

    public Optional<User> findBy(String login, String password) {
        Session session = sessionFactory.openSession();
        Optional<User> user = Optional.empty();
        try (session) {
            Transaction transaction = session.beginTransaction();
            log.info("User " + login + " found");
            user = (Optional<User>) session.createQuery("from users where login = :login and password = :password")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getResultList().stream().findFirst();
            transaction.commit();
        } catch (Exception e) {
            log.warn("User not found by login and password");
            e.printStackTrace();
        }
        return user;
    }

    public Optional<User> findByLogin(String login) {
        Session session = sessionFactory.openSession();
        Optional<User> user = Optional.empty();
        try (session) {
            Transaction transaction = session.beginTransaction();
            user = searchDataBaseByLogin(session, "users", login);
            transaction.commit();
        } catch (Exception e) {
            log.warn("User not found by login");
            e.printStackTrace();
        }
        return user;
    }

    public void updateObserved(Long userId, Long advertId) {
        Session session = sessionFactory.openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            final User user = (User) searchDatabaseById(session, "users", userId).get();
            final Advert advert = (Advert) searchDatabaseById(session, "adverts", advertId).get();

            List<Advert> observedAdverts = user.getObserved();
            observedAdverts.add(advert);
            user.setObserved(observedAdverts);

            session.persist(user);
            transaction.commit();

            log.info("Advert added to observed");
        } catch (Exception e) {
            log.warn("Failed to add advert to observed");
            e.printStackTrace();
        }
    }

    private Optional<Object> searchDatabaseById(Session session, String tableName, Long id){
        return session.createQuery("from " + tableName +
                " where id = :id")
                .setParameter("id", id)
                .getResultList().stream().findFirst();
    }

    private Optional<User> searchDataBaseByLogin(Session session, String tableName, String login){
        return (Optional<User>)session.createQuery("from " + tableName +
                " where login = :id")
                .setParameter("login", login)
                .getResultList().stream().findFirst();
    }
}
