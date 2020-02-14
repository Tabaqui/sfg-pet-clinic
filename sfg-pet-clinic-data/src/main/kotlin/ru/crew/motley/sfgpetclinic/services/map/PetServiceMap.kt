package ru.crew.motley.sfgpetclinic.services.map

import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Pet
import ru.crew.motley.sfgpetclinic.services.PetService

@Service
class PetServiceMap : AbstractMapService<Pet, Long>(), PetService {
    override fun save(entity: Pet): Pet {
        return super.save(entity.getId()!!, entity)
    }
}