package com.happn.worshop.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository {

    Optional<User> findById(UUID id);

    User save(User user);
}
