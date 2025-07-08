package com.happn.workshop.domain

import java.util.UUID

interface UserRepository {

    fun findById(id: UUID): User?
    fun save(user: UserCreate): String
}