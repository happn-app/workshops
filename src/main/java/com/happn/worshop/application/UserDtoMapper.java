package com.happn.worshop.application;

import com.happn.worshop.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserDtoMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.id())
                .name(user.name())
                .email(user.email())
                .build();
    }
}
