package ru.crew.motley.sfgpetclinic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


@SpringBootApplication(exclude = [GsonAutoConfiguration::class])
//@SpringBootApplication(scanBasePackages = ["ru.crew.motley.sfgpetclinic"])
class SfgPetClinicApplication {
    @Controller
    internal class IndexController {
        @GetMapping("/")
        fun index(model: Model): String {
            model.addAttribute("greeting", "Hello, Wro4j!")
            return "index"
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SfgPetClinicApplication>(*args)
}
