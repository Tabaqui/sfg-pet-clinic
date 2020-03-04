package ru.crew.motley.sfgpetclinic.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.model.PetType
import ru.crew.motley.sfgpetclinic.services.OwnerService
import ru.crew.motley.sfgpetclinic.services.PetService
import ru.crew.motley.sfgpetclinic.services.PetTypeService
import java.lang.NullPointerException

private val VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm"

@Controller
@RequestMapping("/owners/{ownerId}")
class PetController(
        private val petService: PetService,
        private val ownerService: OwnerService,
        private val petTypeService: PetTypeService
) {

    @InitBinder("owner")
    fun setAllowedFields(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    @ModelAttribute("types")
    fun populatePetTypes(): Collection<PetType> {
        return petTypeService.findAll()
    }

    @ModelAttribute("owner")
    fun findOwner(@PathVariable ownerId: Long): Owner {
        return ownerService.findById(ownerId) ?: throw NullPointerException()
    }


}