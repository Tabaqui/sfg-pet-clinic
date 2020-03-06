package ru.crew.motley.sfgpetclinic.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.model.Pet
import ru.crew.motley.sfgpetclinic.model.PetType
import ru.crew.motley.sfgpetclinic.services.OwnerService
import ru.crew.motley.sfgpetclinic.services.PetService
import ru.crew.motley.sfgpetclinic.services.PetTypeService
import javax.validation.Valid

const val VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm"

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

    @GetMapping("/pets/new")
    fun initCreationForm(owner: Owner, model: Model): String {
        val pet = Pet(name = "test", owner = owner)
        owner.pets.add(pet)
        pet.owner = owner
        model.addAttribute("pet", pet)
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM
    }

    @PostMapping("/pets/new")
    fun processCreationForm(owner: Owner, @Valid pet: Pet, result: BindingResult, model: Model): String {
        if (pet.name.isNotBlank() && pet.isNew() && owner.getPet(pet.name, true) != null)
            result.rejectValue("name", "duplicate", "already exists")
        owner.pets.add(pet)
        return if (result.hasErrors())
            VIEWS_PETS_CREATE_OR_UPDATE_FORM.also { model.addAttribute("pet", pet) }
        else {
            val saved = petService.save(pet)
            "redirect:/owners/${saved.getId()}"
        }
    }

    @GetMapping("/pets/{petId}/edit")
    fun initUpdateForm(@PathVariable petId: Long, model: Model): String {
        model.addAttribute("pet",petService.findById(petId))
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM
    }

    @PostMapping("/pets/{petId}/edit")
    fun processUpdateForm(@Valid pet: Pet, result: BindingResult, owner: Owner, model: Model) : String {
        return if (result.hasErrors()) {
            pet.owner = owner
            model.addAttribute("pet", pet)
            VIEWS_PETS_CREATE_OR_UPDATE_FORM
        } else {
            owner.pets.add(pet)
            pet.owner = owner
            petService.save(pet)
            "redirect:/owners/${owner.getId()}"
        }
    }


}