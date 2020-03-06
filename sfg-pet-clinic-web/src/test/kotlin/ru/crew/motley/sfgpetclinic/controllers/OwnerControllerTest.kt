package ru.crew.motley.sfgpetclinic.controllers

import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
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
import ru.crew.motley.sfgpetclinic.services.OwnerService

fun <T> anyNotNull(): T = any()

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
    fun findOwners() {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk)
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"))

        verifyNoInteractions(ownerService)
    }

    @Test
    fun processFindFormReturnMany() {
        `when`(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners.toList())

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk)
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize<Any>(owners.size)))
    }

    @Test
    fun processFindFormReturnOne() {
        `when`(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(listOf(Owner("", "").apply { setId(1L) }))

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/owners/1"))
/*                .andExpect(model().attribute("owners", IsNull.notNullValue()))*/
    }

    @Test
    fun displayOwner() {
        `when`(ownerService.findById(anyLong())).thenReturn(Owner("", "").apply { setId(1L) })

        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk)
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", IsNull.notNullValue()))
    }

    @Test
    fun initCreationForm() {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk)
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"))

        verifyNoInteractions(ownerService)
    }

    @Test
    fun processCreationForm() {
        `when`(ownerService.save(anyNotNull())).thenReturn(Owner().apply { setId(1L) })

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"))

        verify(ownerService).save(anyNotNull())
    }

    @Test
    fun initUpdateOwnerForm() {
        `when`(ownerService.findById(anyLong())).thenReturn(Owner().apply { setId(1L) })

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk)
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"))

        verify(ownerService).findById(anyLong())
    }

    @Test
    fun processUpdateOwnerForm() {
        `when`(ownerService.save(anyNotNull())).thenReturn(Owner().apply { setId(1L) })

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"))

        verify(ownerService).save(anyNotNull())
    }
}