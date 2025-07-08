package com.happn.workshop.infra

import com.happn.workshop.domain.User
import com.happn.workshop.domain.UserCreate
import com.happn.workshop.domain.UserRepository
import com.happn.workshop.domain.toUser
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserInMemoryRepository : UserRepository {

    private val users = mutableMapOf<UUID, User>()

    override fun findById(id: UUID): User? = users[id]

    override fun save(user: UserCreate): String = UUID.randomUUID().apply {
        users.put(this, user.toUser(id = this))
    }.toString()

}