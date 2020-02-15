package ru.crew.motley.sfgpetclinic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [GsonAutoConfiguration::class])
class SfgPetClinicApplication

fun main(args: Array<String>) {
    runApplication<SfgPetClinicApplication>(*args)
}
