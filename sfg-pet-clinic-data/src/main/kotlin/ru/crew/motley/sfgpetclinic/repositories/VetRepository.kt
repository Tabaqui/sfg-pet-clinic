package ru.crew.motley.sfgpetclinic.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.crew.motley.sfgpetclinic.model.Vet

@Repository
interface VetRepository : CrudRepository<Vet, Long>