package ru.crew.motley.sfgpetclinic.services.map

import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Pet
import ru.crew.motley.sfgpetclinic.services.PetService

@Service
class PetMapService : AbstractMapService<Pet, Long>(), PetService