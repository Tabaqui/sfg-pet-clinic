package ru.crew.motley.sfgpetclinic.model

import javax.persistence.*

@Entity
@Table(name = "vets")
class Vet(
        firstName: String,
        lastName: String,
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "vet_speciality",
                joinColumns = [JoinColumn(name = "vet_id")],
                inverseJoinColumns = [JoinColumn(name = "speciality_id")])
        var specialities: MutableSet<Speciality> = mutableSetOf()
) : Person(firstName, lastName)