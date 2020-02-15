package ru.crew.motley.sfgpetclinic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [GsonAutoConfiguration::class])
//@SpringBootApplication(scanBasePackages = ["ru.crew.motley.sfgpetclinic"])
class SfgPetClinicApplication

fun main(args: Array<String>) {
    runApplication<SfgPetClinicApplication>(*args)
}
