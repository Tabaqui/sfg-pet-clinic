package ru.crew.motley.sfgpetclinic.services.map

import ru.crew.motley.sfgpetclinic.model.Vet
import ru.crew.motley.sfgpetclinic.services.CrudService

class VetServiceMap: AbstractMapService<Vet, Long>(), CrudService<Vet, Long> {

    override fun save(entity: Vet): Vet {
        return super.save(entity.getId()!!, entity)
    }
}