package ru.crew.motley.sfgpetclinic.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "specialties")
class Speciality(
        @Column(name = "description")
        var description: String
) : AbstractJpaPersistable<Long>()