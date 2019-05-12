package ru.crew.motley.sfgpetclinic.services

import ru.crew.motley.sfgpetclinic.model.Vet

interface VetService {

    fun findById(id: Long): Vet

    fun save(owner: Vet)

    fun findAll(): Set<Vet>

}