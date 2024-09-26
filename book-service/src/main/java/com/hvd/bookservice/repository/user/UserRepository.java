package com.hvd.bookservice.repository.user;

import com.hvd.bookservice.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUsersById(String id);
    User findUserByUsername(String username);
}
