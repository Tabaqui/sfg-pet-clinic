package ru.crew.motley.sfgpetclinic

import org.springframework.format.Formatter
import org.springframework.stereotype.Component
import ru.crew.motley.sfgpetclinic.model.PetType
import ru.crew.motley.sfgpetclinic.services.PetTypeService
import java.text.ParseException
import java.util.*

@Component
class PetTypeFormatter(private val petTypeService: PetTypeService) : Formatter<PetType> {

    override fun print(`object`: PetType, locale: Locale): String {
        return `object`.name
    }

    override fun parse(text: String, locale: Locale): PetType {
        return petTypeService.findAll().find { it.name == text }
                ?: throw ParseException("PetType not found: $text", 0)
    }
}
