package ru.crew.motley.sfgpetclinic.controllers

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.model.Pet
import ru.crew.motley.sfgpetclinic.model.PetType
import ru.crew.motley.sfgpetclinic.services.OwnerService
import ru.crew.motley.sfgpetclinic.services.PetService
import ru.crew.motley.sfgpetclinic.services.PetTypeService

@ExtendWith(MockitoExtension::class)
class PetControllerTest {

    @Mock
    lateinit var petService: PetService

    @Mock
    lateinit var ownerService: OwnerService

    @Mock
    lateinit var petTypeService: PetTypeService

    @InjectMocks
    lateinit var petController: PetController

    lateinit var mockMvc: MockMvc

    lateinit var owner: Owner

    lateinit var petTypes: Set<PetType>

    @BeforeEach
    fun setUp() {
        owner = Owner().apply { setId(1L) }

        petTypes = setOf(
                PetType("Dog").apply { setId(1L) },
                PetType("Cat").apply { setId(2L) }
        )

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build()
    }

    @Test
    fun initCreationForm() {
        `when`(ownerService.findById(anyLong())).thenReturn(owner)
        `when`(petTypeService.findAll()).thenReturn(petTypes)

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk)
//                .andExpect(model().attributeExists("owner"))
//                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM))

        verify(ownerService).findById(anyLong())
        verify(petTypeService).findAll()
    }

    @Test
    fun processCreationForm() {
        `when`(ownerService.findById(anyLong())).thenReturn(owner)
        `when`(petTypeService.findAll()).thenReturn(petTypes)
        `when`(petService.save(anyNotNull())).thenReturn(Pet(owner = null, birthDate = null).apply { setId(1L) })

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("pet"))

        verify(petService).save(anyNotNull())
    }

    @Test
    fun initUpdateForm() {
        `when`(ownerService.findById(anyLong())).thenReturn(owner)
        `when`(petTypeService.findAll()).thenReturn(petTypes)

        `when`(petService.findById(ArgumentMatchers.anyLong())).thenReturn(Pet().apply { setId(1L) })

        mockMvc.perform(get("/owners/${owner.getId()}/pets/1/edit"))
                .andExpect(status().isOk)
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM))
    }

    @Test
    fun processUpdateForm() {
        `when`(ownerService.findById(anyLong())).thenReturn(owner)
        `when`(petTypeService.findAll()).thenReturn(petTypes)

        mockMvc.perform(post("/owners/${owner.getId()}/pets/1/edit"))
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("pet"))

        verify(petService).save(anyNotNull())
    }
}