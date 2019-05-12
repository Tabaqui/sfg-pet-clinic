package ru.crew.motley.sfgpetclinic.services.map

import ru.crew.motley.sfgpetclinic.model.Pet
import ru.crew.motley.sfgpetclinic.services.CrudService

class PetServiceMap : AbstractMapService<Pet, Long>(), CrudService<Pet, Long> {
    override fun save(entity: Pet): Pet {
        return super.save(entity.getId()!!, entity)
    }
}