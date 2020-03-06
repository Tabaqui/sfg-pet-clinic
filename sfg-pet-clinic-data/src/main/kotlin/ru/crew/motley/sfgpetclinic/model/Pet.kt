package ru.crew.motley.sfgpetclinic.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "pets")
class Pet(
        @Column(name = "name")
        var name: String = "",
        @ManyToOne
        @JoinColumn(name = "pet_type_id")
        var petType: PetType? = null,
        @ManyToOne
        @JoinColumn(name = "owner_id")
        var owner: Owner? = null,
        @Column(name = "birth_date")
        var birthDate: LocalDate? = null,
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "pet")
        var visits: MutableSet<Visit> = mutableSetOf()
) : AbstractJpaPersistable<Long>()