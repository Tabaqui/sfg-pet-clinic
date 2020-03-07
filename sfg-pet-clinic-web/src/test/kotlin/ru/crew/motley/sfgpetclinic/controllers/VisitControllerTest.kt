package ru.crew.motley.sfgpetclinic.controllers

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.model.Pet
import ru.crew.motley.sfgpetclinic.model.PetType
import ru.crew.motley.sfgpetclinic.model.Visit
import ru.crew.motley.sfgpetclinic.services.PetService
import ru.crew.motley.sfgpetclinic.services.VisitService
import java.time.LocalDate

private const val PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm"
private const val REDIRECT_OWNERS_1 = "redirect:/owners/{ownerId}"
private const val YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit"

@ExtendWith(MockitoExtension::class)
class VisitControllerTest {

    @Mock
    lateinit var petService: PetService

    @Mock
    lateinit var visitService: VisitService

    @InjectMocks
    lateinit var visitController: VisitController

    private lateinit var mockMvc: MockMvc

    private lateinit var pet: Pet

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build()

        pet = Pet(
                "Cutie",
                PetType("Dog"),
                Owner("Doe", "Joe").apply { setId(1L) },
                LocalDate.of(2018, 11, 13)
        ).apply { setId(1L) }
    }

    @Test
    fun initNewVisitForm() {
        `when`(petService.findById(ArgumentMatchers.anyLong())).thenReturn(pet)

        mockMvc.get("/owners/{ownerId}/pets/{petId}/visits/new", "1", "1")
                .andExpect {
                    status { isOk }
                    view { name(PETS_CREATE_OR_UPDATE_VISIT_FORM) }
                }
    }

    @Test
    fun processNewVisitForm() {
        `when`(petService.findById(ArgumentMatchers.anyLong())).thenReturn(pet)
        `when`(visitService.save(anyNotNull())).thenReturn(Visit(LocalDate.now(), pet = pet).apply { setId(1L) })

        mockMvc.post("/owners/{ownerId}/pets/{petId}/visits/new", "1", "1") {
                    contentType = MediaType.APPLICATION_FORM_URLENCODED
                    param("date", "02/02/02")
                    param("description", YET_ANOTHER_VISIT_DESCRIPTION)
                }
                .andExpect {
                    status { is3xxRedirection }
                    view { name("redirect:/owners/1") }
                    model { attributeExists("visit") }
                }

        verify(visitService).save(anyNotNull())
    }

}