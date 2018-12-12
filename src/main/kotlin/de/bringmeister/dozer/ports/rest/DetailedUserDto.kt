package de.bringmeister.dozer.ports.rest

import de.bringmeister.dozer.NoArgConstructor

@NoArgConstructor
class DetailedUserDto(
    var firstName: String,
    var lastName: String,
    var nickName: String,
    var addresses: List<AddressDto>
)

@NoArgConstructor
class AddressDto(
    var street: String,
    var city: String
)
