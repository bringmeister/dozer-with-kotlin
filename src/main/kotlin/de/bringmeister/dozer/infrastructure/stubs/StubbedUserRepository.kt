package de.bringmeister.dozer.infrastructure.stubs

import de.bringmeister.dozer.domain.User
import de.bringmeister.dozer.domain.UserRepository
import org.springframework.stereotype.Service

@Service
class StubbedUserRepository : UserRepository {

    private val users = mutableMapOf<String, User>()

    override fun find(userId: String): User? {
        return users[userId]
    }

    override fun getAll(): List<User> {
        return users.values.toList()
    }

    override fun save(user: User) {
        users.put(user.userId, user)
    }
}
