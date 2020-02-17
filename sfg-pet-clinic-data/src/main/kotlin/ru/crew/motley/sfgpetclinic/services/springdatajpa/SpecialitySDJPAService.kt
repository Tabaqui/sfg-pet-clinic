package ru.crew.motley.sfgpetclinic.services.springdatajpa

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Speciality
import ru.crew.motley.sfgpetclinic.repositories.SpecialityRepository
import ru.crew.motley.sfgpetclinic.services.SpecialityService

@Service
@Profile("springdatajpa")
class SpecialitySDJPAService(
        private val specialityRepository: SpecialityRepository
) : SpecialityService {

    override fun findAll(): Set<Speciality> {
        return specialityRepository.findAll().toSet()
    }

    override fun findById(id: Long): Speciality? {
        return specialityRepository.findById(id).orElse(null)
    }

    override fun save(entity: Speciality): Speciality {
        return specialityRepository.save(entity)
    }

    override fun delete(entity: Speciality) {
        specialityRepository.delete(entity)
    }

    override fun deleteById(id: Long) {
        specialityRepository.deleteById(id)
    }
}