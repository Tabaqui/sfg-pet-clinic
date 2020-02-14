package ru.crew.motley.sfgpetclinic.services.map

import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Vet
import ru.crew.motley.sfgpetclinic.services.VetService

@Service
class VetServiceMap: AbstractMapService<Vet, Long>(), VetService {

    override fun save(entity: Vet): Vet {
        return super.save(entity.getId()!!, entity)
    }
}