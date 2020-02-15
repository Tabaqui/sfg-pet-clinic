package ru.crew.motley.sfgpetclinic.bootstrap

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.model.Pet
import ru.crew.motley.sfgpetclinic.model.PetType
import ru.crew.motley.sfgpetclinic.model.Vet
import ru.crew.motley.sfgpetclinic.services.OwnerService
import ru.crew.motley.sfgpetclinic.services.PetTypeService
import ru.crew.motley.sfgpetclinic.services.VetService

@Component
class DataLoader(
        private val ownerService: OwnerService,
        private val vetService: VetService,
        private val petTypeService: PetTypeService
) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String?) {

        val dog = PetType("Dog")
        val savedDogPetType = petTypeService.save(dog)

        val cat = PetType("Cat")
        val savedCatPetType = petTypeService.save(cat)

        val owner1 = Owner("Michael", "Weston")
        ownerService.save(owner1)

        val owner2 = Owner("Fiona", "Glenanne")
        ownerService.save(owner2)

        println("Loaded Owners...")

        val vet1 = Vet("Sam", "Axe")
        vetService.save(vet1)

        val vet2 = Vet("Jessie", "Porter")
        vetService.save(vet2)

        println("Loaded Vets...")

    }
}