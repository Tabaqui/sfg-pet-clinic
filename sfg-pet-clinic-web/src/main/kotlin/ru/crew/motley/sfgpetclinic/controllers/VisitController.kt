package ru.crew.motley.sfgpetclinic.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import ru.crew.motley.sfgpetclinic.model.Visit
import ru.crew.motley.sfgpetclinic.services.PetService
import ru.crew.motley.sfgpetclinic.services.VisitService
import java.time.LocalDate
import javax.validation.Valid

@Controller
class VisitController(
        private val visitService: VisitService,
        private val petService: PetService) {

    @InitBinder
    fun setAllowedFields(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    @ModelAttribute("visit")
    fun loadPetWithVisit(@PathVariable("petId") petId: Long, model: Model): Visit {
        val pet = petService.findById(petId) ?: throw RuntimeException("Pet not found with id: $petId")
        model.addAttribute("pet", pet)
        val visit = Visit(LocalDate.now(), pet = pet)
        pet.visits.add(visit)
        return visit
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    fun initNewVisitForm(@PathVariable("petId") petId: Long, model: Model): String {
        return "pets/createOrUpdateVisitForm"
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    fun processNewVisitForm(@Valid visit: Visit, result: BindingResult) : String {
        return if (result.hasErrors())
            "pets/createOrUpdateVisitForm"
        else {
            val saved = visitService.save(visit)
            "redirect:/owners/${saved.getId()}"
        }
    }

}