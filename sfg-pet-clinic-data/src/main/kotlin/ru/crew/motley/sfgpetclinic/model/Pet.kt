package ru.crew.motley.sfgpetclinic.model

import java.time.LocalDate

class Pet(
        val petType: PetType,
        val owner: Owner,
        val birthDate: LocalDate
)