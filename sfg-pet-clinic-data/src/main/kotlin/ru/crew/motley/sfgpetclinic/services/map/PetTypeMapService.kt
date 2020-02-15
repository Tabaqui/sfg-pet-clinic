package ru.crew.motley.sfgpetclinic.services.map

import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.PetType
import ru.crew.motley.sfgpetclinic.services.PetTypeService

@Service
class PetTypeMapService : AbstractMapService<PetType, Long>(), PetTypeService