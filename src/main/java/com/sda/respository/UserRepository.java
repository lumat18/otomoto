package com.sda.respository;

import com.sda.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRepository {

  private static UserRepository userRepository;

  private List<User> users;

  public static UserRepository aUserRepository() {
    if (userRepository == null) {
      userRepository = new UserRepository(new ArrayList<>());
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
