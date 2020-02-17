package ru.crew.motley.sfgpetclinic.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.crew.motley.sfgpetclinic.model.Owner

@Repository
interface OwnerRepository : CrudRepository<Owner, Long> {

    fun findByLastName(lastName: String): Owner
}