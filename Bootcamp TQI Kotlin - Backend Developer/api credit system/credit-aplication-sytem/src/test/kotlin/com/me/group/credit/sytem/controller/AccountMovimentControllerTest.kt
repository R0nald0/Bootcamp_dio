package com.me.group.credit.sytem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.me.group.credit.sytem.dto.AccountMovimentDTO
import com.me.group.credit.sytem.dto.toAccountMoviment
import com.me.group.credit.sytem.entity.Account
import com.me.group.credit.sytem.entity.AccountMovement
import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.TitulosMovimentacao
import com.me.group.credit.sytem.repository.AccountMovimentRepository
import com.me.group.credit.sytem.repository.CustomerRepository
import com.me.group.credit.sytem.service.serviceImpl.AccountMovimentService
import com.me.group.credit.sytem.service.serviceImpl.CustomerServiceImpl
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.jmx.support.ObjectNameManager
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.client.match.MockRestRequestMatchers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.MultiValueMap
import java.math.BigDecimal
import java.util.*

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration
@ExtendWith(MockKExtension::class)
class AccountMovimentControllerTest {


    @Autowired
    lateinit var customerRespositoryMockMvc:CustomerRepository

    @MockK
    lateinit var accountMovimentService: AccountMovimentService

    @MockK
    lateinit var customerServiceImpl: CustomerServiceImpl

    @Autowired
    lateinit var mockMvc :MockMvc

    @Autowired
    lateinit var objectManager: ObjectMapper

    companion object{
        const val URL_ACCOUNTMOVIMENT = "/api/movimentation"
    }
  @BeforeEach
    fun setUp() {

    }

    @Test
    fun `saveAccountMoviment_most return save customer e and return customer saved`() {
           every { accountMovimentService
               .saveAccountMoviment(any())}.returns(getAccounMovimentDto().toAccountMoviment())

        val asString = objectManager.writeValueAsString(getAccounMovimentDto())
        mockMvc.perform(MockMvcRequestBuilders.post("${URL_ACCOUNTMOVIMENT}/save")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asString)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
           .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("PEDIDO_EMPRESTIMO"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.movimentValue").value("1300"))
            .andDo(MockMvcResultHandlers.print())
    }
   @Test
    fun `getAllMovimentsAccount_should return all moviments by customer`() {
         every { accountMovimentService.getAllAccontMovimentCostumer(1)}.returns(getAccountMoviments())

             val idcustomer = 1L
             val stringId = objectManager.writeValueAsString(idcustomer)
           mockMvc.perform(MockMvcRequestBuilders.get("$URL_ACCOUNTMOVIMENT/${idcustomer}")
               .contentType(MediaType.APPLICATION_JSON)
               .content(stringId)
           )
               .andExpect(MockMvcResultMatchers.status().isOk)
               //.andExpect(MockMvcResultMatchers.jsonPath("$.].type").value("TitulosMovimentacao.TED"))
               .andDo(MockMvcResultHandlers.print())
    }

        @Test
        fun `getAccountMovimentById_should return a account moviment of the customer by id`() {

            every { accountMovimentService.findAccountMoviment(1,1)}
                .returns(getAccountMoviments()[0])
            val valueAsString = objectManager.writeValueAsString(1)
            mockMvc.perform(MockMvcRequestBuilders
                .get("$URL_ACCOUNTMOVIMENT/accountmoviments/1?idmoviment=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
                .content("1")
            ).andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("TitulosMovimentacao.TED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movimentValue").value(1230.0))
                .andDo(MockMvcResultHandlers.print())
        }

    @AfterEach
    fun tearDown() {

    }
    fun getAccount() = Account(
        numberAccount = Random().nextLong(100000,999999),
        accountBalanceBlocked = BigDecimal.valueOf(2000L),
        movements = mutableListOf(),
        accountFreeBalance = BigDecimal.valueOf(3000L)
    )
    fun buildCustomer() = Customer(
        fistName =  "Miau",
        lastName= "Silva",
        cpf = "28475934625",
        email= "miau@email.com",
        password = "12345",
        address = Address(
            zipCode = "3232",
            street = " rrererr "
        ),
        income= BigDecimal.valueOf(1000.0),
        account = getAccount(),
    )
    fun getAccountMoviments() = listOf(
       AccountMovement(
           id = 1,
           dateMoviment = Date().time,
           type = TitulosMovimentacao.TED,
           movimentValue = "1230.0".toBigDecimal(),
           customer = buildCustomer()
       ),
       AccountMovement(
           id = 2,
           dateMoviment = Date().time,
           type = TitulosMovimentacao.PIX,
           movimentValue = "3230.0".toBigDecimal(),
           customer = buildCustomer()
       ),
  )
    fun getAccounMovimentDto() =AccountMovimentDTO(
       dateMoviment = Date().time,
       type = TitulosMovimentacao.PEDIDO_EMPRESTIMO,
       movimentValue = BigDecimal.valueOf(1300L),
       idCustomer = 1
   )

}