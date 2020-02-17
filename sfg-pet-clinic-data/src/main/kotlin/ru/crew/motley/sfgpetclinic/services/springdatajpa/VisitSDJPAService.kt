package ru.crew.motley.sfgpetclinic.services.springdatajpa

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Visit
import ru.crew.motley.sfgpetclinic.repositories.VisitRepository
import ru.crew.motley.sfgpetclinic.services.VisitService

@Service
@Profile("springdatajpa")
class VisitSDJPAService(
        private val visitRepository: VisitRepository
) : VisitService {

    override fun findAll(): Set<Visit> {
        return visitRepository.findAll().toSet()
    }

    override fun findById(id: Long): Visit? {
        return visitRepository.findById(id).orElse(null)
    }

    override fun save(entity: Visit): Visit {
        return visitRepository.save(entity)
    }

    override fun delete(entity: Visit) {
        visitRepository.delete(entity)
    }

    override fun deleteById(id: Long) {
        visitRepository.deleteById(id)
    }
}