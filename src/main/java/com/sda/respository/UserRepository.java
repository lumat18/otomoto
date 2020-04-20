package com.sda.respository;

import com.sda.hibernate.HibernateUtil;
import com.sda.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRepository {

  private static UserRepository userRepository;
  private SessionFactory sessionFactory;

  private List<User> users;

  public static UserRepository aUserRepository() {
    if (userRepository == null) {
      userRepository = new UserRepository(HibernateUtil.getSessionFactory(), new ArrayList<>());
    }
    return userRepository;
  }

  public boolean save(User user) {
    Optional<User> existingUser = users.stream()
        .filter(u -> u.getLogin().equals(user.getLogin()))
        .findAny();
    if (existingUser.isPresent()) {
      return false;
    }
    users.add(user);

    Session session = sessionFactory.openSession();
    try (session) {
      Transaction transaction = session.beginTransaction();
      System.out.println("Saving " + user.getLogin() + " to mySQL database");
      session.persist(user);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }


    return true;
  }

  public Optional<User> findBy(String login, String password) {
    return users.stream()
        .filter(u -> u.getLogin().equalsIgnoreCase(login) && u.getPassword().equals(password))
        .findAny();
  }

  public List<User> findAll() {
    return Collections.unmodifiableList(users);
  }

  public User findByLogin(String login){
    return users.stream()
            .filter(user -> user.getLogin().equals(login))
            .findAny()
            .get();
  }
}
