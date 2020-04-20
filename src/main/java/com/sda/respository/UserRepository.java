package com.sda.respository;

import com.sda.hibernate.HibernateUtil;
import com.sda.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (existingUser.isPresent()) {
            return false;
        }

        Session session = sessionFactory.openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Saving " + user.getLogin() + " to database");
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        List<User> users = new ArrayList<>();
        try (session) {
            Transaction transaction = session.beginTransaction();
            users = (List<User>) session.createQuery("from users").getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error when loading users from database");
            e.printStackTrace();
        }
        return users;
    }

    public Optional<User> findBy(String login, String password) {
      Session session = sessionFactory.openSession();
      Optional<User> user = Optional.empty();
      try (session) {
        Transaction transaction = session.beginTransaction();
        user = (Optional<User>) session.createQuery("from users where login = :login and password = :password")
                .setParameter("login", login)
                .setParameter("password", password)
                .getResultList().stream().findFirst();
        transaction.commit();
      } catch (Exception e) {
        System.out.println("Error user not found in database");
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
        System.out.println("Error user not found in database");
        e.printStackTrace();
      }
      return user;
    }
}
