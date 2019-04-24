package by.bsuir.flylink.service.impl;

import by.bsuir.flylink.model.User;
import by.bsuir.flylink.repository.UserRepository;
import by.bsuir.flylink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findByLoginOrEmail(String login, String email) {
        return userRepository.findByLoginOrEmail(login, email);
    }

    @Override
    public Boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
