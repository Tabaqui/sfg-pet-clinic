package ru.crew.motley.sfgpetclinic.model

import java.time.LocalDate

class Pet(
        var name: String,
        var petType: PetType,
        var owner: Owner,
        var birthDate: LocalDate
): AbstractJpaPersistable<Long>()