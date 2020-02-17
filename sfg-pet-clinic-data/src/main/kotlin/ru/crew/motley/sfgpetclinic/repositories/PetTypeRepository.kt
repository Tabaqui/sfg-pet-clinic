package ru.crew.motley.sfgpetclinic.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.crew.motley.sfgpetclinic.model.PetType

@Repository
interface PetTypeRepository : CrudRepository<PetType, Long>