package de.bringmeister.dozer.application

import de.bringmeister.dozer.domain.User
import de.bringmeister.dozer.domain.UserRepository
import de.bringmeister.dozer.ports.rest.CreateUserDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun createUser(createUserDto: CreateUserDto) {

        val user = User(createUserDto.password)
        user.changeName(createUserDto.firstName, createUserDto.lastName)
        user.changeNickname(createUserDto.nickName)
        createUserDto.addresses.forEach { user.addAddress(it.street, it.city) }

        userRepository.save(user)
        log.info("User created. [userId={}]", user.userId)
    }
}
