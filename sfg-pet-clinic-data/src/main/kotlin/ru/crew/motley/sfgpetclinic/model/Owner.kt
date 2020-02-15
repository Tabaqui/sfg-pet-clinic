package ru.crew.motley.sfgpetclinic.model

class Owner(
        firstName: String,
        lastName: String,
        var pets: Set<Pet>)
    : Person(firstName, lastName)