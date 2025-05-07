package com.happn.worshop.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record User(UUID id, String name, String email) {
}
