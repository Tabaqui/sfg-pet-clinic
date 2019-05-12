package ru.crew.motley.sfgpetclinic.services

import ru.crew.motley.sfgpetclinic.model.Owner

interface OwnerService {

    fun findByLastName(lastName: String)

    fun findById(id: Long): Owner

    fun save(owner: Owner)

    fun findAll(): Set<Owner>

}