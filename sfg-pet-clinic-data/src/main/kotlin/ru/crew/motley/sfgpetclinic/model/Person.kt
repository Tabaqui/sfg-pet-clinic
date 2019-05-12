package ru.crew.motley.sfgpetclinic.model

open class Person(val firstName: String, val lastName: String): AbstractJpaPersistable<Long>()