package com.happn.worshop.application;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDto(UUID id, String name, String email) {
}
