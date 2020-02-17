package ru.crew.motley.sfgpetclinic.services.springdatajpa

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Pet
import ru.crew.motley.sfgpetclinic.repositories.PetRepository
import ru.crew.motley.sfgpetclinic.services.PetService

@Service
@Profile("springdatajpa")
class PetSDJPAService(
        private val petRepository: PetRepository
) : PetService {

    override fun findAll(): Set<Pet> {
        return petRepository.findAll().toSet()
    }

    override fun findById(id: Long): Pet? {
        return petRepository.findById(id).orElse(null)
    }

    override fun save(entity: Pet): Pet {
        return petRepository.save(entity)
    }

    override fun delete(entity: Pet) {
        petRepository.delete(entity)
    }

    override fun deleteById(id: Long) {
        petRepository.deleteById(id)
    }
}