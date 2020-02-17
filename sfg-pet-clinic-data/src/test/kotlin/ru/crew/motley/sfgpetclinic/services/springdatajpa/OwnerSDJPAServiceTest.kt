package ru.crew.motley.sfgpetclinic.services.springdatajpa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.repositories.OwnerRepository
import ru.crew.motley.sfgpetclinic.repositories.PetRepository
import ru.crew.motley.sfgpetclinic.repositories.PetTypeRepository
import java.util.*


fun <T> anyNotNull(): T = Mockito.any<T>()

@ExtendWith(MockitoExtension::class)
class OwnerSDJPAServiceTest {

    @Mock
    lateinit var ownerRepository: OwnerRepository
    @Mock
    lateinit var petRepository: PetRepository
    @Mock
    lateinit var petTypeRepository: PetTypeRepository

    @InjectMocks
    lateinit var service: OwnerSDJPAService

    val LAST_NAME = "Smith"

    lateinit var returnOwner: Owner

    @BeforeEach
    fun setUp() {
        returnOwner = Owner("", LAST_NAME).apply { setId(1L) }
    }

    @Test
    fun findByLastName() {
        `when`(ownerRepository.findByLastName(anyNotNull())).thenReturn(returnOwner)

        val owner = service.findByLastName(LAST_NAME)

        assertEquals(LAST_NAME, owner.lastName)

        verify(ownerRepository).findByLastName(anyNotNull())
    }

    @Test
    fun findAll() {
        val returnOwnersSet = setOf<Owner>(
                Owner("", "").apply { setId(1L) },
                Owner("", "").apply { setId(2L) }
        )

        `when`(ownerRepository.findAll()).thenReturn(returnOwnersSet)

        val owners = service.findAll()

        assertEquals(2, owners.size)
    }

    @Test
    fun findById() {
        `when`(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner))

        val owner = service.findById(1L)

        assertNotNull(owner)
    }

    @Test
    fun findByIdNotFound() {
        `when`(ownerRepository.findById(anyLong())).thenReturn(Optional.empty())

        val owner = service.findById(1L)

        assertNull(owner)
    }

    @Test
    fun save() {
        val owner = Owner("", "").apply { 1L }

        `when`<Owner>(ownerRepository.save(any())).thenReturn(returnOwner)

        val savedOwner = service.save(owner)

        assertNotNull(savedOwner)

        verify(ownerRepository).save(any())
    }

    @Test
    fun delete() {
        service.delete(returnOwner)

        verify(ownerRepository).delete(any())
    }

    @Test
    fun deleteById() {
        service.deleteById(1L)

        verify(ownerRepository).deleteById(anyLong())
    }
}