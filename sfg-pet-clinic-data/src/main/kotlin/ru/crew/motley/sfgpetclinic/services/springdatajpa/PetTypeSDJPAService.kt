package ru.crew.motley.sfgpetclinic.services.springdatajpa

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.PetType
import ru.crew.motley.sfgpetclinic.repositories.PetTypeRepository
import ru.crew.motley.sfgpetclinic.services.PetTypeService

@Service
@Profile("springdatajpa")
class PetTypeSDJPAService(
        private val petTypeRepository: PetTypeRepository
) : PetTypeService {
    override fun findAll(): Set<PetType> {
        return petTypeRepository.findAll().toSet()
    }

    override fun findById(id: Long): PetType? {
        return petTypeRepository.findById(id).orElse(null)
    }

    override fun save(entity: PetType): PetType {
        return petTypeRepository.save(entity)
    }

    override fun delete(entity: PetType) {
        petTypeRepository.delete(entity)
    }

    override fun deleteById(id: Long) {
        petTypeRepository.deleteById(id)
    }
}