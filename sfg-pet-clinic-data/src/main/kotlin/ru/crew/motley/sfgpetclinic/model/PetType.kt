package ru.crew.motley.sfgpetclinic.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "pet_type")
class PetType(
        @Column(name = "name")
        val name: String
) : AbstractJpaPersistable<Long>()
