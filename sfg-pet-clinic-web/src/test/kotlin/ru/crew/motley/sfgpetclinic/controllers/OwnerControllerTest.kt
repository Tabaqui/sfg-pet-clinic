package ru.crew.motley.sfgpetclinic.controllers

import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import ru.crew.motley.sfgpetclinic.model.Owner
import ru.crew.motley.sfgpetclinic.services.OwnerService

@ExtendWith(MockitoExtension::class)
internal class OwnerControllerTest {

    @Mock
    private lateinit var ownerService: OwnerService

    @InjectMocks
    private lateinit var controller: OwnerController

    private lateinit var owners: Set<Owner>
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        owners = setOf(
                Owner("", "").apply { setId(1L) },
                Owner("", "").apply { setId(2L) }
        )

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun listOwners() {
        `when`(ownerService.findAll()).thenReturn(owners)
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk)
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize<Any>(2)))
    }

    @Test
    fun listOwnersByIndex() {
        `when`(ownerService.findAll()).thenReturn(owners)
        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk)
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize<Any>(2)))
    }


    @Test
    fun findOwners() {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk)
                .andExpect(view().name("notimplemented"))

        verifyNoInteractions(ownerService)
    }
}