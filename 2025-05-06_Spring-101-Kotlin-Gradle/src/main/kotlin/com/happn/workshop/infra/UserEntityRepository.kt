package com.happn.workshop.infra

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserEntityRepository : CrudRepository<UserEntity, UUID>