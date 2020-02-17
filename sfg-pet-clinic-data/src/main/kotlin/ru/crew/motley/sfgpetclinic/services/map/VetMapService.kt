package ru.crew.motley.sfgpetclinic.services.map

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Vet
import ru.crew.motley.sfgpetclinic.services.SpecialityService
import ru.crew.motley.sfgpetclinic.services.VetService

@Service
@Profile("default", "map")
class VetMapService(
        private val specialityService: SpecialityService
)
    : AbstractMapService<Vet, Long>(), VetService {

    override fun save(entity: Vet): Vet {
        entity.specialities.forEach {
            val savedSpeciality = specialityService.save(it)
            it.setId(savedSpeciality.getId()!!)
        }

        return super.save(entity)
    }
}