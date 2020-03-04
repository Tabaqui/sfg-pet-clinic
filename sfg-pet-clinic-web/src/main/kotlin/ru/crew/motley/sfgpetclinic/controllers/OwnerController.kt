package ru.crew.motley.sfgpetclinic.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.DataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.services.OwnerService
import javax.validation.Valid

@Controller
class OwnerController(private val ownerService: OwnerService) {

    @InitBinder
    fun setAllowedFields(dataBinder: DataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    @RequestMapping(path = ["/owners", "/owners/index", "/owners/index.html"])
    fun listOwners(model: Model): String {

        model.addAttribute("owners", ownerService.findAll())

        return "owners/index"
    }

    @RequestMapping(path = ["/owners/find"])
    fun findOwners(model: Model): String {

        model.addAttribute("owner", Owner("", ""))

        return "owners/findOwners"
    }

    @GetMapping("/owners")
    fun processFindForm(owner: Owner?, result: BindingResult, model: Model): String {

        val results = if (owner?.lastName != null)
            ownerService.findAllByLastNameLike("%${owner.lastName}%")
        else
            listOf()

        return when {
            results.isEmpty() -> {
                result.rejectValue("lastName", "notFound", "not found")
                "owners/findOwners"
            }
            results.size == 1 -> {
                "redirect:/owners/" + results.first().getId()
            }
            else -> {
                model.addAttribute("selections", results)
                "owners/ownersList"
            }
        }
    }

    @GetMapping("/owners/{ownerId}")
    fun showOwner(@PathVariable("ownerId") ownerId: Long): ModelAndView {
        val owner = ownerService.findById(ownerId) ?: throw RuntimeException("Owner not found.")
        return ModelAndView("owners/ownerDetails").apply { addObject(owner) }
    }

    @GetMapping("/owners/new")
    fun initCreationForm(model: Model): String {
        model.addAttribute("owner", Owner())
        return "owners/createOrUpdateOwnerForm"
    }

    @PostMapping("/owners/new")
    fun processCreationForm(@Valid owner: Owner, result: BindingResult): String {
        return if (result.hasErrors())
            "owners/createOrUpdateOwnerForm"
        else {
            val saved = ownerService.save(owner)
            "redirect:/owners/${saved.getId()}"
        }
    }

    @GetMapping("/owners/{ownerId}/edit")
    fun initUpdateOwnerForm(@PathVariable("ownerId") ownerId: Long, model: Model): String {
        model.addAttribute(ownerService.findById(ownerId) ?: throw NullPointerException())
        return "owners/createOrUpdateOwnerForm"
    }

    @PostMapping("/owners/{ownerId}/edit")
    fun processUpdateOwnerForm(@PathVariable("ownerId") ownerId: Long, @Valid owner: Owner, result: BindingResult): String {
        return if (result.hasErrors())
            "owners/createOrUpdateOwnerForm"
        else {
            owner.setId(ownerId)
            val saved = ownerService.save(owner)
            "redirect:/owners/${saved.getId()}"
        }
    }

}