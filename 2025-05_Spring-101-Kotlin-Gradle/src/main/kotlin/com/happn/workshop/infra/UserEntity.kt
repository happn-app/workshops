package com.happn.workshop.infra

import com.happn.workshop.domain.User
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity(name = "users")
data class UserEntity(
    @Id
    val id: UUID,
    val name: String,
    val email: String
)


fun UserEntity.toDomain(): User = User(
    id = this.id,
    name = this.name,
    email = this.email
)

fun User.toEntity(): UserEntity = UserEntity(
    id = this.id,
    name = this.name,
    email = this.email
)