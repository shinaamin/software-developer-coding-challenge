package com.traderev.carauctionsystem.service;

import com.google.common.base.Preconditions;
import com.traderev.carauctionsystem.model.User;
import com.traderev.carauctionsystem.repo.UserRepository;
import com.traderev.carauctionsystem.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-11
 */

@Service
public class UserServiceImplementation implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        logger.debug("Executing saveUser method in UserServiceImplementation class");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Long userId) {
        logger.debug("Executing updateUser method in UserServiceImplementation class");
        Preconditions.checkArgument(user.getUserId() == userId, "User Id does not match");

        User usr = findUserById(userId);
        if (usr != null) {
            user.setUserId(userId);
            userRepository.save(user);
            return user;
        } else {
            throw new ResourceNotFoundException(userId.toString(), "user not found");
        }
    }

    @Override
    public List<User> findAllUsers() {
        logger.debug("Executing findAllUsers method in UserServiceImplementation class");
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(e -> userList.add(e));
        return userList;
    }

    @Override
    public User findUserById(Long id) {
        logger.debug("Executing findUserById method in UserServiceImplementation class");
        Optional<User> user = userRepository.findById(id);
        try {
            return user.get();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void deleteUserById(Long userId) {
        logger.debug("Executing deleteUserById method in UserServiceImplementation class");
        Preconditions.checkArgument(checkUserExistsById(userId), "user does not exists");
        userRepository.deleteById(userId);
    }

    @Override
    public boolean checkUserExistsById(Long id) {
        logger.debug("Executing checkUserExistsById method in UserServiceImplementation class");
        return userRepository.existsById(id);

    }


}
