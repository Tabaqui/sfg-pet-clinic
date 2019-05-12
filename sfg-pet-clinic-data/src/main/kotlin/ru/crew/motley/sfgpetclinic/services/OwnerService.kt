package ru.crew.motley.sfgpetclinic.services

import ru.crew.motley.sfgpetclinic.model.Owner

interface OwnerService : CrudService<Owner, Long> {

    fun findByLastName(lastName: String)

}