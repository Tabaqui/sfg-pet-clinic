package ru.crew.motley.sfgpetclinic.services.map

import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.services.CrudService

class OwnerServiceMap : AbstractMapService<Owner, Long>(), CrudService<Owner, Long> {
    override fun save(entity: Owner) = super.save(entity.getId()!!, entity)
}