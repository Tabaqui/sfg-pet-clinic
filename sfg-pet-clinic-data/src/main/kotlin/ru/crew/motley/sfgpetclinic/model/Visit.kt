package ru.crew.motley.sfgpetclinic.model

import java.time.LocalDate

class Visit(
        var date: LocalDate,
        var description: String,
        var pet: Pet
) : AbstractJpaPersistable<Long>()