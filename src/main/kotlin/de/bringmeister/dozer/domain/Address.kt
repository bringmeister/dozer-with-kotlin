package de.bringmeister.dozer.domain

import de.bringmeister.dozer.NoArgConstructor

@NoArgConstructor
data class Address(
    var street: String,
    var city: String
)