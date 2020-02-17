package ru.crew.motley.sfgpetclinic.services.map

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Pet
import ru.crew.motley.sfgpetclinic.services.PetService

@Service
@Profile("default", "map")
class PetMapService : AbstractMapService<Pet, Long>(), PetService