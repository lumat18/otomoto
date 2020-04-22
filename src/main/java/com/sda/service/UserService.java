package com.sda.service;

import com.sda.model.User;
import com.sda.request.EditUserRequest;
import com.sda.respository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static UserService userService;

    private UserRepository userRepository;

    public static UserService aUserService() {
        if (userService == null) {
            userService = new UserService(UserRepository.aUserRepository());
        }
        return userService;
    }

    public boolean saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserBy(String login, String password) {
        return userRepository.findBy(login, password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login).get();
    }

    public void editUser(EditUserRequest request) {
        final User user = userRepository.findByLogin(request.getLogin()).get();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
    }
}
