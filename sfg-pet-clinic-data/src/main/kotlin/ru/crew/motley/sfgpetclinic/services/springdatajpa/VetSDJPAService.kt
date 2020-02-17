package ru.crew.motley.sfgpetclinic.services.springdatajpa

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Vet
import ru.crew.motley.sfgpetclinic.repositories.VetRepository
import ru.crew.motley.sfgpetclinic.services.VetService

@Service
@Profile("springdatajpa")
class VetSDJPAService(
        private val vetRepository: VetRepository
) : VetService {

    override fun findAll(): Set<Vet> {
        return vetRepository.findAll().toSet()
    }

    override fun findById(id: Long): Vet? {
        return vetRepository.findById(id).orElse(null)
    }

    override fun save(entity: Vet): Vet {
        return vetRepository.save(entity)
    }

    override fun delete(entity: Vet) {
        vetRepository.delete(entity)
    }

    override fun deleteById(id: Long) {
        vetRepository.deleteById(id)
    }
}