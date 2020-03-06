package ru.crew.motley.sfgpetclinic.model

import javax.persistence.*

@Entity
@Table(name = "owners")
class Owner(
        firstName: String = "",
        lastName: String = "",
        @Column(name = "address")
        var address: String = "",
        @Column(name = "city")
        var city: String = "",
        @Column(name = "telephone")
        var telephone: String = "",
        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "owner")
        var pets: MutableSet<Pet> = mutableSetOf())
    : Person(firstName, lastName) {

    fun getPet(name: String): Pet? {
        return getPet(name, false)
    }

    fun getPet(name: String, ignoreNew: Boolean): Pet? {
        return pets.filterNot { ignoreNew && it.isNew() }.find { it.name?.toLowerCase() == name.toLowerCase() }
    }
}