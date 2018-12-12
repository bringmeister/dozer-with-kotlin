package de.bringmeister.dozer.domain

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.Assert.state
import java.util.UUID

class User(password: String) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    val userId: String
    private val password: String
    lateinit var firstName: String
        private set
    lateinit var lastName: String
        private set
    lateinit var nickName: String
        private set
    val addresses = mutableListOf<Address>()

    init {

        state(password.length > 5, "Password too short!")

        this.userId = UUID.randomUUID().toString()
        this.password = password

        log.info("New user ID has been initialized. [userId=$userId, password=******]")
    }

    fun changeName(firstName: String, lastName: String) {

        state(firstName.isNotBlank(), "First name must not be blank! [userId=$userId]")
        state(lastName.isNotBlank(), "Last name must not be blank! [userId=$userId]")

        this.firstName = firstName
        this.lastName = lastName

        log.info("Name of user has been updated. [userId=$userId, firstName=$firstName, lastName=$lastName]")
    }

    fun changeNickname(nickName: String) {

        state(nickName.isNotBlank(), "Nickname must not be blank! [userId=$userId]")

        this.nickName = nickName

        log.info("Nickname of user has been updated. [userId=$userId, nickName=$nickName]")
    }

    fun addAddress(street: String, city: String) {

        state(street.isNotBlank(), "Street must not be blank! [userId=$userId]")
        state(city.isNotBlank(), "City must not be blank! [userId=$userId]")

        this.addresses.add(Address(street, city))

        log.info("Address added to user. [userId=$userId, street=$street, city=$city]")
    }
}
