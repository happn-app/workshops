package com.happn.workshop.application

import com.happn.workshop.domain.UserCreate

data class UserCreateDto(
    val name: String,
    val email: String,
)

fun UserCreateDto.toDomain() = UserCreate(
    name = name,
    email = email,
)