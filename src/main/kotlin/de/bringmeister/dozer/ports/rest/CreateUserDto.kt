package de.bringmeister.dozer.ports.rest

data class CreateUserDto(
    val firstName: String,
    val lastName: String,
    val nickName: String,
    val password: String,
    val addresses: List<CreateAddressDto>
)

data class CreateAddressDto(
    var street: String,
    var city: String
)
