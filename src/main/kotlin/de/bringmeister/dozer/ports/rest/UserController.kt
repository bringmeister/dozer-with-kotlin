package de.bringmeister.dozer.ports.rest

import de.bringmeister.dozer.application.UserService
import de.bringmeister.dozer.domain.UserRepository
import org.dozer.DozerBeanMapperBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val userRepository: UserRepository
) {

    private val dozer = DozerBeanMapperBuilder.buildDefault()

    @PostMapping("/users")
    fun createUser(@RequestBody createUserDto: CreateUserDto) {
        userService.createUser(createUserDto)
    }

    @GetMapping("/users")
    fun getAllUsers(): List<SimpleUserDto> {
        return userRepository
            .getAll()
            .map { dozer.map(it, SimpleUserDto::class.java) }
    }

    @GetMapping("/users/{userId}")
    fun findUser(@PathVariable userId: String): DetailedUserDto? {
        return userRepository
            .find(userId)
            ?.let { dozer.map(it, DetailedUserDto::class.java) }
    }
}
