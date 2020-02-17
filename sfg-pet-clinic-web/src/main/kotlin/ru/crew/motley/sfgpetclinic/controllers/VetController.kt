package ru.crew.motley.sfgpetclinic.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import ru.crew.motley.sfgpetclinic.services.VetService

@Controller
class VetController(private val vetsService: VetService) {

    @RequestMapping(path = ["/vets", "/vets.html", "/vets/index", "/vets/index.html"])
    fun listVets(model: Model): String {

        model.addAttribute("vets", vetsService.findAll())

        return "vets/index"
    }
}