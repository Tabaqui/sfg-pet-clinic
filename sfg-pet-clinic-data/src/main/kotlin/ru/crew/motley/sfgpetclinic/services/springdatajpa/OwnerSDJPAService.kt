package ru.crew.motley.sfgpetclinic.services.springdatajpa

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.repositories.OwnerRepository
import ru.crew.motley.sfgpetclinic.repositories.PetRepository
import ru.crew.motley.sfgpetclinic.repositories.PetTypeRepository
import ru.crew.motley.sfgpetclinic.services.OwnerService

@Service
@Profile("springdatajpa")
class OwnerSDJPAService(
        private val ownerRepository: OwnerRepository,
        private val petRepository: PetRepository,
        private val petTypeRepository: PetTypeRepository
) : OwnerService {


    override fun findByLastName(lastName: String): Owner {
        return ownerRepository.findByLastName(lastName)
    }

    override fun findAll(): Set<Owner> {
        return ownerRepository.findAll().toSet()
    }

    override fun findById(id: Long): Owner? {
        return ownerRepository.findById(id).orElse(null)
    }

    override fun save(entity: Owner): Owner {
        return ownerRepository.save(entity)
    }

    override fun delete(entity: Owner) {
        ownerRepository.delete(entity)
    }

    override fun deleteById(id: Long) {
        ownerRepository.deleteById(id)
    }
}