package ru.crew.motley.sfgpetclinic.services.map

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import ru.crew.motley.sfgpetclinic.model.Speciality
import ru.crew.motley.sfgpetclinic.services.SpecialityService

@Service
@Profile("default", "map")
class SpecialityMapService : AbstractMapService<Speciality, Long>(), SpecialityService