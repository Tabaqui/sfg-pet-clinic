package ru.crew.motley.sfgpetclinic.services.map

import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.services.OwnerService

@Service
class OwnerServiceMap : AbstractMapService<Owner, Long>(), OwnerService {

    override fun findByLastName(lastName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}