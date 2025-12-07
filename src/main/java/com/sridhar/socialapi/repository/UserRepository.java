package com.sridhar.socialapi.repository;

import com.sridhar.socialapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String userName);

    Boolean existsByUsername(String username);
}
