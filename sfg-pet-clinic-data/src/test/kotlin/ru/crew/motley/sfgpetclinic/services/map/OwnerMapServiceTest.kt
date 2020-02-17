package ru.crew.motley.sfgpetclinic.services.map

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.crew.motley.sfgpetclinic.model.Owner

class OwnerMapServiceTest {

    private val OWNER_ID = 1L
    private val LAST_NAME = "Smith"

    lateinit var ownerMapService: OwnerMapService

    @BeforeEach
    fun setUp() {
        ownerMapService = OwnerMapService(PetTypeMapService(), PetMapService())
        ownerMapService.save(Owner("", LAST_NAME).apply { setId(OWNER_ID) })
    }

    @Test
    fun findAll() {
        val owners = ownerMapService.findAll()
        assertEquals(1, owners.size)
    }

    @Test
    fun findById() {
        val owner = ownerMapService.findById(OWNER_ID)
        assertEquals(OWNER_ID, owner!!.getId())
    }

    @Test
    fun saveExistingId() {
        val id = 2L
        val owner = Owner("", "").apply { setId(id) }
        val savedOwner = ownerMapService.save(owner)

        assertEquals(id, savedOwner.getId())
    }

    @Test
    fun saveNoId() {
        val owner = Owner("", "")
        val savedOwner = ownerMapService.save(owner)

        assertNotNull(savedOwner)
        assertNotNull(savedOwner.getId())
    }

    @Test
    fun delete() {
        ownerMapService.delete(ownerMapService.findById(OWNER_ID)!!)

        assertEquals(0, ownerMapService.findAll().size)
    }

    @Test
    fun deleteById() {
        ownerMapService.deleteById(OWNER_ID)

        assertEquals(0, ownerMapService.findAll().size)
    }

    @Test
    fun findByLastName() {
        val owner = ownerMapService.findByLastName(LAST_NAME)

        assertNotNull(owner)
        assertEquals(OWNER_ID, owner?.getId())
    }

    @Test
    fun findByLastNameNotFound() {
        val owner = ownerMapService.findByLastName("foo")

        assertNull(owner)
    }
}