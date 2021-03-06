package ru.crew.motley.sfgpetclinic.bootstrap

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ru.crew.motley.sfgpetclinic.model.*
import ru.crew.motley.sfgpetclinic.services.*
import java.time.LocalDate

@Component
class DataLoader(
        private val ownerService: OwnerService,
        private val vetService: VetService,
        private val petTypeService: PetTypeService,
        private val specialityService: SpecialityService,
        private val visitService: VisitService
) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String?) {
        if (petTypeService.findAll().isEmpty())
            loadData()
    }

    private fun loadData() {
        val dog = PetType("Dog")
        val savedDogPetType = petTypeService.save(dog)

        val cat = PetType("Cat")
        val savedCatPetType = petTypeService.save(cat)

        val radiology = Speciality("radiology")
        val savedRadiology = specialityService.save(radiology)

        val surgery = Speciality("surgery")
        val savedSurgery = specialityService.save(surgery)

        val dentistry = Speciality("dentistry")
        val savedDentistry = specialityService.save(dentistry)

        val owner1 = Owner("Michael", "Weston", "123 Brickerel", "Miami", "1231231234")
        val mikesPet = Pet("Rosco", savedDogPetType, owner1, LocalDate.now())
        owner1.pets.add(mikesPet)
        ownerService.save(owner1)

        val owner2 = Owner("Fiona", "Glenanne", "123 Brickerel", "Miami", "1231231234")
        val fionaCat = Pet("Just Cat", savedCatPetType, owner2, LocalDate.now())
        owner2.pets.add(fionaCat)
        ownerService.save(owner2)

        val catVisit = Visit(LocalDate.now(), "Sneezy Kitty", fionaCat)
        visitService.save(catVisit)

        println("Loaded Owners...")

        val vet1 = Vet("Sam", "Axe")
        vet1.specialities.add(savedRadiology)
        vetService.save(vet1)

        val vet2 = Vet("Jessie", "Porter")
        vet2.specialities.add(savedSurgery)
        vetService.save(vet2)

        println("Loaded Vets...")
    }
}