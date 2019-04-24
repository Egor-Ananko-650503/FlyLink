package by.bsuir.flylink.service;

import by.bsuir.flylink.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByLoginOrEmail(String login, String email);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);

    User saveUser(User user);
}
