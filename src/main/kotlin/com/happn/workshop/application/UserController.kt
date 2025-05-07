package com.happn.workshop.application

import com.happn.workshop.domain.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): UserDto =
        userService.findById(UUID.fromString(id))?.toDto() ?: throw IllegalStateException("User not found")

    @PostMapping
    fun save(@RequestBody userCreation: UserCreateDto): String = userService.save(user = userCreation.toDomain())

}