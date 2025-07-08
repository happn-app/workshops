package com.happn.worshop.infrastructure;

import com.happn.worshop.domain.User;
import com.happn.worshop.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserInMemoryRepository implements UserRepository {

    private Map<UUID, User> users = new HashMap<>();

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User save(User user) {
        users.put(user.id(), user);
        return user;
    }
}
