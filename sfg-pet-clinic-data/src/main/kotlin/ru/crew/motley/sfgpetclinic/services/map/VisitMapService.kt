package ru.crew.motley.sfgpetclinic.services.map

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Visit
import ru.crew.motley.sfgpetclinic.services.VisitService

@Service
@Profile("default", "map")
class VisitMapService : AbstractMapService<Visit, Long>(), VisitService {

    override fun save(entity: Visit): Visit {
        if (entity.pet.getId() == null || entity.pet.owner.getId() == null)
            throw RuntimeException("Invalid Visit")

        return super.save(entity)
    }
}