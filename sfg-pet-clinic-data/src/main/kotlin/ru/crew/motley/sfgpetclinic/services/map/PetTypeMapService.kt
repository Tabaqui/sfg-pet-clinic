package ru.crew.motley.sfgpetclinic.services.map

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.PetType
import ru.crew.motley.sfgpetclinic.services.PetTypeService

@Service
@Profile("default", "map")
class PetTypeMapService : AbstractMapService<PetType, Long>(), PetTypeService