package ru.crew.motley.sfgpetclinic.model

class Vet(
        firstName: String,
        lastName: String,
        var specialities: MutableSet<Speciality> = mutableSetOf()
) : Person(firstName, lastName)