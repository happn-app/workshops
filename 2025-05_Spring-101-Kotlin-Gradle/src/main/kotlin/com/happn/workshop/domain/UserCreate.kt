package com.happn.workshop.domain

import java.util.UUID

data class UserCreate(
    val name: String,
    val email: String,
)

fun UserCreate.toUser(id: UUID) = User(
    id = id,
    name = name,
    email = email,
)