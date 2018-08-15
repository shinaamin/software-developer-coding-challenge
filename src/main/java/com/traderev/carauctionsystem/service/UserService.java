package com.traderev.carauctionsystem.service;

import com.traderev.carauctionsystem.model.User;

import java.util.List;

/**
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-11
 */

public interface UserService {

    User saveUser(User user);

    User updateUser(User user, Long userId);

    List<User> findAllUsers();

    User findUserById(Long id);

    void deleteUserById(Long id);

    boolean checkUserExistsById(Long id);
}
