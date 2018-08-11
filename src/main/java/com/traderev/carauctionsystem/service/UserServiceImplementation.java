package com.traderev.carauctionsystem.service;

import com.google.common.base.Preconditions;
import com.traderev.carauctionsystem.model.User;
import com.traderev.carauctionsystem.repo.UserRepository;
import com.traderev.carauctionsystem.exception.ResourceNotFoundException;
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
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Long userId) {
        Preconditions.checkArgument(user.getUserId() == userId, "User Id does not match");

        User usr = getUserById(userId);
        if (usr != null) {
            user.setUserId(userId);
            userRepository.save(user);
            return user;
        } else {
            throw new ResourceNotFoundException(userId.toString(), "user not found");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(e -> userList.add(e));
        return userList;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        try {
            return user.get();
        } catch (Exception ex) {
            return null;
        }
     }

    @Override
    public void deleteUserById(Long userId) {
        boolean userExists = checkUserExistsById(userId);
        if (userExists) {
            userRepository.deleteById(userId);
        }
    }

    @Override
    public boolean checkUserExistsById(Long id) {
        return userRepository.existsById(id);

    }


}
