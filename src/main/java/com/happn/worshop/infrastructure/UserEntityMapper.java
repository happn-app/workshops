package com.happn.worshop.infrastructure;

import com.happn.worshop.domain.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserEntityMapper {

    public static User toModel(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }

    public static UserEntity toEntity(User user){
        return  UserEntity.builder()
                .id(user.id())
                .email(user.email())
                .name(user.name())
                .build();
    }

}
