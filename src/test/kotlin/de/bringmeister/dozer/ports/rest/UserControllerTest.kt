package de.bringmeister.dozer.ports.rest

import de.bringmeister.dozer.application.UserService
import de.bringmeister.dozer.domain.User
import de.bringmeister.dozer.domain.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @MockBean
    private lateinit var userRepository: UserRepository

    @Test
    fun `should create new user`() {

        mvc
            .perform(
                post("/users")
                    .contentType(APPLICATION_JSON)
                    .content(
                        """
                            {
                                "firstName": "Jon",
                                "lastName": "Doe",
                                "nickName": "jonny3000",
                                "password": "123456",
                                "addresses": [
                                    { "street": "Teststreet 42", "city": "Testcity" }
                                ]
                            }
                    """.trimIndent()
                    )
            )
            .andExpect(status().isOk)

        verify(userService).createUser(
            CreateUserDto(
                firstName = "Jon",
                lastName = "Doe",
                nickName = "jonny3000",
                password = "123456",
                addresses = listOf(CreateAddressDto(street = "Teststreet 42", city = "Testcity"))
            )
        )
    }

    @Test
    fun `should return all users`() {

        val user = User("123456")
        user.changeNickname("jonny3000")

        doReturn(listOf(user)).`when`(userRepository).getAll()

        mvc
            .perform(get("/users"))
            .andExpect(status().isOk)
            .andExpect(
                content()
                    .string("""[{"nickName":"jonny3000"}]""")
            )
    }

    @Test
    fun `should return user`() {

        val user = User("123456")
        user.changeNickname("jonny3000")
        user.changeName("Jon", "Doe")

        doReturn(user).`when`(userRepository).find(user.userId)

        mvc
            .perform(get("/users/${user.userId}"))
            .andExpect(status().isOk)
            .andExpect(
                content()
                    .string("""{"firstName":"Jon","lastName":"Doe","nickName":"jonny3000","addresses":[]}""")
            )
    }
}
