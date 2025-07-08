package com.happn.workshop.domain

import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val email: String,
)
