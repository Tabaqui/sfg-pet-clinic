package ru.crew.motley.sfgpetclinic.services.map

import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.services.OwnerService
import ru.crew.motley.sfgpetclinic.services.PetService
import ru.crew.motley.sfgpetclinic.services.PetTypeService

@Service
class OwnerServiceMap(
        private val petTypeService: PetTypeService,
        private val petService: PetService
) : AbstractMapService<Owner, Long>(), OwnerService {

    override fun save(entity: Owner): Owner {
        entity.pets.forEach {
            it.petType = petTypeService.save(it.petType)
            val savedPet = petService.save(it)
            it.setId(savedPet.getId()!!)
        }

        return super.save(entity)
    }

    override fun findByLastName(lastName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}