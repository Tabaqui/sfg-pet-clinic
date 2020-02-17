package ru.crew.motley.sfgpetclinic.model

class Owner(
        firstName: String,
        lastName: String,
        var address: String = "",
        var city: String = "",
        var telephone: String = "",
        var pets: MutableSet<Pet> = mutableSetOf())
    : Person(firstName, lastName)