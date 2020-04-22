package com.sda.respository;

import com.sda.hibernate.HibernateUtil;
import com.sda.model.User;
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
            user = (Optional<User>) session.createQuery("from users where login = :login")
                    .setParameter("login", login)
                    .getResultList().stream().findFirst();
            transaction.commit();
        } catch (Exception e) {
            log.warn("User not found by login");
            e.printStackTrace();
        }
        return user;
    }
}
