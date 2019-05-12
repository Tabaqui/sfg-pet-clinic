package ru.crew.motley.sfgpetclinic.services

import ru.crew.motley.sfgpetclinic.model.Pet

interface PetService {

    fun findById(id: Long): Pet

    fun save(owner: Pet)

    fun findAll(): Set<Pet>

}