package de.bringmeister.dozer.domain

interface UserRepository {
    fun find(userId: String): User?
    fun getAll(): List<User>
    fun save(user: User)
}