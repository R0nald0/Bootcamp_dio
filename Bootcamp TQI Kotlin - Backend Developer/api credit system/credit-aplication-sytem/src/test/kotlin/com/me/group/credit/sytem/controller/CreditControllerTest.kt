package com.me.group.credit.sytem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.me.group.credit.sytem.dto.CreditDTO
import com.me.group.credit.sytem.entity.Account
import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.Status
import com.me.group.credit.sytem.repository.CreditRepository
import com.me.group.credit.sytem.repository.CustomerRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
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
import java.time.LocalDate
import java.util.*

@ActiveProfiles("Test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class CreditControllerTest {
    @Autowired
    lateinit var creditRepositoryMock : CreditRepository

    @Autowired
    lateinit var  customerRepository: CustomerRepository

    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var  objectMapper :ObjectMapper

    companion object{
        const val URL_CREDIT = "/api/credit"
    }
    lateinit var customerMock : Customer
    @BeforeEach
    fun setUp() {
        customerRepository.deleteAll()
        creditRepositoryMock.deleteAll()
        customerMock   =customerRepository.save(getCustomer())
    }

    @Test
    fun `saveCredit_must create credit and return status code 201`() {
         val customerMock  = customerRepository.save(getCustomer())
         val credit = creditRepositoryMock.save(credit.copy(customer = customerMock))

         val stringCreditDto = objectMapper.writeValueAsString(creditDto)

        mockMvc.perform(MockMvcRequestBuilders.post(
            "${URL_CREDIT}/save"
           ).contentType(MediaType.APPLICATION_JSON)
            .content(stringCreditDto))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.idCustomer").value("1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.creditValue").value("1234.3"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfInstallment").value(2))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `getAllMovimentsAccount_must return all credits by id of customer`() {

         listCredit.forEach {
             creditRepositoryMock.save(it.copy(customer = customerMock))
         }
        val  idString = objectMapper.writeValueAsString(1)
        mockMvc.perform(MockMvcRequestBuilders.get("$URL_CREDIT/credits?customerId=1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(idString))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `findByCreditCode_must return credit by credit code and customer id`() {
            val uuid = UUID.randomUUID()
            val creditCodeMock = creditRepositoryMock.save(credit.copy(customer = customerMock, creditCode = uuid))
            val creditCodeString  =objectMapper.writeValueAsString(uuid)
            val customerIdString  =objectMapper.writeValueAsString(customerMock.id)

            mockMvc.perform(MockMvcRequestBuilders.get("$URL_CREDIT/credit/${uuid}?customerId=${customerMock.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(creditCodeString)
                .content(customerIdString))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditValue").value("1234.3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfInstallment").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("IN_PROGRESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idCustomer").value(1))

                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `updateStateCredit_must upadate credit by id e and id customer e retunr credit upadated`() {

            val creditMockReturn = creditRepositoryMock.save(credit.copy( customer = customerMock))
             val uudi = creditMockReturn.creditCode
            val idCustomeString = objectMapper.writeValueAsString( creditMockReturn.customer?.id)
            val stateCredit = objectMapper.writeValueAsString(Status.APPROVED)
            val  creditCodeString = objectMapper.writeValueAsString(creditMockReturn.creditCode)

            mockMvc.perform(MockMvcRequestBuilders
                .patch("$URL_CREDIT/update/${creditMockReturn.creditCode}?customerId=${creditMockReturn.customer?.id}?stateCredit=${Status.APPROVED}")
                .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(MockMvcResultHandlers.print())
    }

    @AfterEach
    fun tearDown() {
        customerRepository.deleteAll()
        creditRepositoryMock.deleteAll()
    }

    fun getCustomer()= Customer(
         fistName = "Miau",
        lastName = "Silva",
        "03656811806",
        3000.0.toBigDecimal(),
        "miau@email.com",
        "21313",
        Address(
            zipCode = "321344", street = "rua do peixe"
        ),
        mutableListOf(),
        account = getAccount(),
        1 ,
    )
     fun getAccount() = Account(
         numberAccount = Random().nextLong(100000,999999),
        accountBalanceBlocked = BigDecimal.valueOf(2000L),
        movements = mutableListOf(),
        accountFreeBalance = BigDecimal.valueOf(3000L)
    )


    val listCredit = listOf(
        Credit(
            creditValue = 1234.3.toBigDecimal(),
            customer = getCustomer(), id = 1,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 2,
            dayFirstInstallment = Date().time,
            creditCode = UUID.randomUUID()
        ),
        Credit(
            creditValue = 1340.3.toBigDecimal(),
            customer = getCustomer(), id = 1,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 3,
            dayFirstInstallment = Date().time,
            creditCode = UUID.randomUUID()
        ),
        Credit(
            creditValue = 1234.3.toBigDecimal(),
            customer = getCustomer(),
            id = 1,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 2,
            dayFirstInstallment = LocalDate.now().plusMonths(1).toEpochDay(),
            creditCode = UUID.randomUUID()
        )
    )
   val credit  = Credit(
        creditValue = 1234.3.toBigDecimal(),
        customer =Customer(
            "Miau",
            "Silva",
            "03656811806",
            3000.0.toBigDecimal(),
            "miau@email.com",
            "21313",
            Address(
                zipCode = "321344", street = "rua do peixe"
            ),
            mutableListOf(),
            account=getAccount(),
            getCustomer().id,
            ),
        id =1,
        status = Status.IN_PROGRESS,
        numberOfInstallments = 2,
        dayFirstInstallment = Date().time.plus(10L),
        creditCode = UUID.randomUUID()
    )

    val  creditDto=  CreditDTO(
        creditValue = 1234.3.toBigDecimal(),
        customerId = 1,
        numberOfInstallment = 2,
        dayOfInstallment = "15/08/2023",
    )


}