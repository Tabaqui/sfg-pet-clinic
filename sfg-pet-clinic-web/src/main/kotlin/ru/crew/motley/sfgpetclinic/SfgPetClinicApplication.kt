package ru.crew.motley.sfgpetclinic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["ru.crew.motley.sfgpetclinic"])
class SfgPetClinicApplication

fun main(args: Array<String>) {
    runApplication<SfgPetClinicApplication>(*args)
}
