package com.traderev.carauctionsystem.repo;

import com.traderev.carauctionsystem.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Crud Repository class for User entity operations.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-10
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailId(String emailId);
}
