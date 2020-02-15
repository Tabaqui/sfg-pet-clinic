package ru.crew.motley.sfgpetclinic.model

open class Person(
        var firstName: String,
        var lastName: String
) : AbstractJpaPersistable<Long>()