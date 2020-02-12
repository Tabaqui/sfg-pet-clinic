package ru.crew.motley.sfgpetclinic.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class OwnerController {

    @RequestMapping(path = ["/owners", "/owners/index", "/owners/index.html"])
    fun listOwners(): String {
        return "owners/index"
    }
}