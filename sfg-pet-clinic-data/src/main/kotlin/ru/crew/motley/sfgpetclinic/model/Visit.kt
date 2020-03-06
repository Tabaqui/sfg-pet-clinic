package ru.crew.motley.sfgpetclinic.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "visits")
class Visit(
        @Column(name = "date")
        var date: LocalDate,
        @Column(name = "description")
        var description: String = "",
        @ManyToOne
        @JoinColumn(name = "pet_id")
        var pet: Pet
) : AbstractJpaPersistable<Long>()