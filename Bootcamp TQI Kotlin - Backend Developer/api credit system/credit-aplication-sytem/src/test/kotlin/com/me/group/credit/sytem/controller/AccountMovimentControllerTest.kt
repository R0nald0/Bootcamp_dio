package com.me.group.credit.sytem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.me.group.credit.sytem.dto.AccountMovimentDTO
import com.me.group.credit.sytem.entity.Account
import com.me.group.credit.sytem.entity.AccountMovement
import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.MovimentationType
import com.me.group.credit.sytem.service.serviceImpl.AccountMovimentService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
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
import java.util.*

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration
@ExtendWith(MockKExtension::class)
class AccountMovimentControllerTest(

) {

     @MockkBean
    lateinit var accountMovimentService: AccountMovimentService

    @Autowired
    lateinit var  mockMvc: MockMvc

    @Autowired
    lateinit var objectManager: ObjectMapper

    companion object{
        const val URL_ACCOUNTMOVIMENT = "/api/movimentation"
    }

    lateinit var accountMovimentcontroller : AccountMovimentController

  @BeforeEach
    fun setUp() {
        accountMovimentcontroller = AccountMovimentController(accountMovimentService)
    }

    @Test
    fun `saveAccountMoviment_most return save customer e and return customer saved`() {
        val accountMovement = getAccounMoviment()
        val movimentDto = getAccounMovimentDto()

        every { accountMovimentService.saveAccountMoviment(any()) } returns accountMovement

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
         every { accountMovimentService.getAllAccontMovimentCostumer(any())} returns(getAccountMoviments())

             val idcustomer = 1L
             val stringId = objectManager.writeValueAsString(idcustomer)
           mockMvc.perform(MockMvcRequestBuilders.get("$URL_ACCOUNTMOVIMENT/${idcustomer}")
               .contentType(MediaType.APPLICATION_JSON)
               .content(stringId)
           )
               .andExpect(MockMvcResultMatchers.status().isOk)
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("TED"))
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
           type = MovimentationType.TED,
           movimentValue = "1230.0".toBigDecimal(),
           customer = buildCustomer()
       ),
       AccountMovement(
           id = 2,
           dateMoviment = Date().time,
           type = MovimentationType.PIX,
           movimentValue = "3230.0".toBigDecimal(),
           customer = buildCustomer()
       ),
  )
    fun getAccounMovimentDto() =AccountMovimentDTO(
       dateMoviment = Date().time,
       type = MovimentationType.PEDIDO_EMPRESTIMO,
       movimentValue = BigDecimal.valueOf(1300L),
       idCustomer = 1
   )
    fun getAccounMoviment() =AccountMovement(
       dateMoviment = Date().time,
       type = MovimentationType.PEDIDO_EMPRESTIMO,
       movimentValue = BigDecimal.valueOf(1300L),
       customer = buildCustomer()
   )

}