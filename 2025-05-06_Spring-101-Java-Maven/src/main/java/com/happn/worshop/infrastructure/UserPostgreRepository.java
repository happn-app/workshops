package com.happn.worshop.infrastructure;

import com.happn.worshop.domain.User;
import com.happn.worshop.domain.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static com.happn.worshop.infrastructure.UserEntityMapper.toEntity;
import static com.happn.worshop.infrastructure.UserEntityMapper.toModel;

@Repository
@Transactional
@Primary
@RequiredArgsConstructor
public class UserPostgreRepository implements UserRepository {

    private final UserEntityRepository userEntityRepository;

    @Override
    public Optional<User> findById(UUID id) {
        return userEntityRepository.findById(id)
                .map(UserEntityMapper::toModel);
    }

    @Override
    public User save(User user) {
        userEntityRepository.save(toEntity(user));
        return user;
    }
}
