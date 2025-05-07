package com.happn.workshop.infra

import com.happn.workshop.domain.User
import com.happn.workshop.domain.UserCreate
import com.happn.workshop.domain.UserRepository
import com.happn.workshop.domain.toUser
import org.springframework.stereotype.Repository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import java.util.*

@Repository
@Transactional
class UserPostgresRepository(private val entityRepository: UserEntityRepository) : UserRepository {

    override fun findById(id: UUID): User? = entityRepository.findByIdOrNull(id)?.toDomain()

    override fun save(user: UserCreate): String = UUID.randomUUID().apply {
        entityRepository.save(user.toUser(this).toEntity())
    }.toString()

}