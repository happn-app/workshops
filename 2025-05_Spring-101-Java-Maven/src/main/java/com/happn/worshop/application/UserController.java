package com.happn.worshop.application;

import com.happn.worshop.domain.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.happn.worshop.application.UserDtoMapper.toDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable UUID id) {

        return userService.findById(id)
                .map(UserDtoMapper::toDto)
                .orElseThrow();
    }

    @PostMapping
    public UserDto create(@RequestBody UserCreateDto userCreateDto) {
        return toDto(userService.createUser(userCreateDto.name(), userCreateDto.email()));
    }
}
