package ru.crew.motley.sfgpetclinic.model

class Vet(
        firstName: String,
        lastName: String,
        var specialities: Set<Speciality> = setOf()
) : Person(firstName, lastName)