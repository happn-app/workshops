package com.happn.workshop.domain

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    @Qualifier("userPostgresRepository")
    private val repo: UserRepository,
) {
    fun findById(id: UUID): User? = repo.findById(id)
    fun save(user: UserCreate) : String = repo.save(user)
}