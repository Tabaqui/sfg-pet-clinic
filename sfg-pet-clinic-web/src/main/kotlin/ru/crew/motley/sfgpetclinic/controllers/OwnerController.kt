package ru.crew.motley.sfgpetclinic.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import ru.crew.motley.sfgpetclinic.services.OwnerService

@Controller
class OwnerController(private val ownerService: OwnerService) {

    @RequestMapping(path = ["/owners", "/owners/index", "/owners/index.html"])
    fun listOwners(model: Model): String {

        model.addAttribute("owners", ownerService.findAll())

        return "owners/index"
    }

    @RequestMapping(path = ["/owners/find"])
    fun findOwners(): String {
        return "notimplemented"
    }

    @GetMapping("/owners/{ownerId}")
    fun showOwner(@PathVariable("ownerId") ownerId: Long): ModelAndView {
        val owner = ownerService.findById(ownerId) ?: throw RuntimeException("Owner not found.")
        return ModelAndView("owners/ownerDetails").apply { addObject(owner) }
    }
}