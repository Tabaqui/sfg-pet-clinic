package ru.crew.motley.sfgpetclinic.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import ru.crew.motley.sfgpetclinic.services.OwnerService

@Controller
class OwnerController(private val ownerService: OwnerService) {

    @RequestMapping(path = ["/owners", "/owners/index", "/owners/index.html"])
    fun listOwners(model: Model): String {

        model.addAttribute("owners", ownerService.findAll())

        return "owners/index"
    }
}