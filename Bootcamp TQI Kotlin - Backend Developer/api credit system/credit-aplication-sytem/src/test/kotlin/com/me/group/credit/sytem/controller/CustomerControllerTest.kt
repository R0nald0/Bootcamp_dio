package com.me.group.credit.sytem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.me.group.credit.sytem.dto.CostumerDTO
import com.me.group.credit.sytem.dto.toEnttity
import com.me.group.credit.sytem.repository.CustomerRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@ActiveProfiles("Test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class CustomerControllerTest {
  @Autowired
   private lateinit var customerRepository: CustomerRepository
   @Autowired
   private lateinit var  mockMvc: MockMvc
   @Autowired
   private lateinit var objectManager: ObjectMapper  //convert clas

   companion object{
       const val URL_API = "/api/customers"
   }

    @BeforeEach
    fun setUp() {
        customerRepository.deleteAll()
    }

    @Test
    fun `most create customer and have code 201 created `() {
         val stringCustomerDTO = objectManager.writeValueAsString(customerr)

        mockMvc.perform(MockMvcRequestBuilders.post("${URL_API}/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringCustomerDTO))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.fistName").value("Miau"))
                .andDo(MockMvcResultHandlers.print())
    }


    @Test
    fun `must return erro and 409 status when save same customer`() {
        customerRepository.save(customerr.toEnttity())
        val stringCustomerDto =objectManager.writeValueAsString(customerr)

        mockMvc.perform(MockMvcRequestBuilders.post("${URL_API}/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringCustomerDto)
        ).andExpect(MockMvcResultMatchers.status().isConflict)
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Conflict"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("org.springframework.dao.DataIntegrityViolationException"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.timeStamp").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").isNotEmpty)
                .andDo(MockMvcResultHandlers.print())

    }

    @Test
    fun `must return status 400 and bad request when fields is empty`() {

        val stringCustomerDto =objectManager.writeValueAsString(customerr.copy(fistName = ""))

        mockMvc.perform(MockMvcRequestBuilders.post("${URL_API}/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringCustomerDto)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad request"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("org.springframework.dao.MethodArgumentNotValidException"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.timeStamp").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").isNotEmpty)
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `must find customer by id with code status equals 200`() {
         val customerRetun = customerRepository.save(customerr.toEnttity())

        mockMvc.perform(MockMvcRequestBuilders.get("${URL_API}/${customerRetun.id}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.fistName").value("Miau"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Silva"))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `findById_when not find id must show status 400 and BadRequest`() {
        val id = 2L
        mockMvc.perform(MockMvcRequestBuilders.get("${URL_API}/$id")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("400"))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `delete_most return 200 status true when idCustomer is valid`() {
        val customerRetun = customerRepository.save(customerr.toEnttity())

        mockMvc.perform(MockMvcRequestBuilders.delete("${URL_API}/${customerRetun.id}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string("true"))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `delete_most return 400 status and false when idCustomer is invalid`() {
        val customerRetun = 33

        mockMvc.perform(MockMvcRequestBuilders.delete("${URL_API}/${customerRetun}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.timeStamp").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("com.me.group.credit.sytem.exeception.BusinessException"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").isNotEmpty)
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `upadteCosutumer_must update customer by id and retunr customerUpdate with status 200`() {
         val cus = customerRepository.save(customerr.toEnttity())
        val customerString = objectManager.writeValueAsString(customerUpdate)

        mockMvc.perform(MockMvcRequestBuilders.patch("${URL_API}?customerId=${cus.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerString)
        ).andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.fistName")
                         .value("Miaus"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName")
                         .value("Cat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode")
                         .value("00000002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street")
                         .value("rua do cat"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.income")
                         .value("2500.0"))
                .andDo(MockMvcResultHandlers.print())




    }

    @Test
    fun `upadteCosutumer_must status 400 and bad request when id invalid or some field is empty`() {
        val cus = customerRepository.save(customerr.toEnttity())
        val customerString = objectManager.writeValueAsString(customerUpdate)
        val customeridInvalid = 321

        mockMvc.perform(MockMvcRequestBuilders.patch("${URL_API}?customerId=${customeridInvalid}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerString)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.jsonPath("$.title")
                        .value("Bad Request"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.timeStamp").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exception")
                        .value("com.me.group.credit.sytem.exeception.BusinessException"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").isNotEmpty)
                .andDo(MockMvcResultHandlers.print())




    }

    @AfterEach
    fun tearDown() {
        customerRepository.deleteAll()
    }

    val customerr = CostumerDTO(
            fistName =   "Miau",lastName= "Silva",
            cpf = "28475934625",
            email= "miau@email.com",
            zipCode = "3232",
            street = " rrererr ",
            income= BigDecimal.valueOf(1000.0),
            passoword = "123414",
            )
    val customerUpdate = CostumerDTO(
            fistName =   "Miaus",lastName= "Cat",
            cpf = "28475934625",
            email= "miau@email.com",
            zipCode = "00000002",
            street = "rua do cat",
            income= BigDecimal.valueOf(2500.0),
            passoword = "123414",
    )
}