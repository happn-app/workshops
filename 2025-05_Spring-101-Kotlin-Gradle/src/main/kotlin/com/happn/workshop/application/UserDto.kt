package com.happn.workshop.application

import com.happn.workshop.domain.User
import java.util.UUID

data class UserDto(
    val id: UUID,
    val name: String,
    val email: String,
)

fun User.toDto() = UserDto(
    id = id,
    name = name,
    email = email
)