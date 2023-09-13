package com.me.group.credit.sytem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.me.group.credit.sytem.dto.CreditDTO
import com.me.group.credit.sytem.entity.Account
import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.Status
import com.me.group.credit.sytem.service.serviceImpl.CreditService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.web.JsonPath
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ExtendWith(SpringExtension::class)
class CreditControllerTest {


    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var creditServiceMock : CreditService

    @Autowired
    lateinit var  objectMapper :ObjectMapper

    companion object{
        const val URL_CREDIT = "/api/credit"
    }

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `saveCredit_must create credit and return status code 201`() {

      every { creditServiceMock.save(creditDto) }.returns(credit)
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
    fun `findAllCreditByCustomer_must return all credits by id of customer`() {

        every { creditServiceMock.findAllByCostumer(any()) }.returns(listCredit)

        val  idString = objectMapper.writeValueAsString(1)
        mockMvc.perform(MockMvcRequestBuilders.get("$URL_CREDIT/credits?customerId=1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(idString))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `findByCreditCode_must return credit by credit code and customer id`() {

        every { creditServiceMock.findByCreditCode(any(),any()) }.returns(credit)

             val uuid = "d75405e8-65cd-4bae-a0dc-1ab890cb80d6"
            val customerIdString  =objectMapper.writeValueAsString(1)

            mockMvc.perform(MockMvcRequestBuilders.get("$URL_CREDIT/credit/${uuid}?customerId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(uuid)
                .content(customerIdString))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditValue").value("1234.3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfInstallment").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("IN_PROGRESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idCustomer").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditCode").value("d75405e8-65cd-4bae-a0dc-1ab890cb80d6"))
                .andDo(MockMvcResultHandlers.print())

    }

    @Test
    fun `updateStateCredit_must update credit by id e and id customer e retunr credit upadated`() {

        every { creditServiceMock.updateStateCredit(any(),any(),any())}.returns(creditUpdateReturn)

            mockMvc.perform(MockMvcRequestBuilders
                .patch("$URL_CREDIT/update/${1}?creditId=1&creditState=${Status.APPROVED}")
                .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(MockMvcResultMatchers.status().isOk)
                    .andExpect(MockMvcResultMatchers.jsonPath("$.creditCode").value("d75405e8-65cd-4bae-a0dc-1ab890cb80d6"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("APPROVED"))
                    .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `getDateMinimum_must return minimum limit date to loan application`(){
          every { creditServiceMock.getDateLimit() }.returns(LocalDate.now().toEpochDay())

        mockMvc.perform(MockMvcRequestBuilders
                .get("$URL_CREDIT/limitDate")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.limitDate").value("31/12/1969 - 21:00:19"))
                .andDo(MockMvcResultHandlers.print())
    }

    @AfterEach
    fun tearDown() {
    }

    fun getCustomer()= Customer(
         fistName = "Miau",
        lastName = "Silva",
        "10175137080",
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
            creditCode = UUID.fromString("a75405e8-65cd-4bae-a0dc-1ab890cb80d6")
        ),
        Credit(
            creditValue = 1340.3.toBigDecimal(),
            customer = getCustomer(), id = 1,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 3,
            dayFirstInstallment = Date().time,
                creditCode = UUID.fromString("c75405e8-65cd-4bae-a0dc-1ab890cb80d6")
        ),
        Credit(
            creditValue = 1234.3.toBigDecimal(),
            customer = getCustomer(),
            id = 1,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 2,
            dayFirstInstallment = LocalDate.now().plusMonths(1).toEpochDay(),
                creditCode = UUID.fromString("b75405e8-65cd-4bae-a0dc-1ab890cb80d6")
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
           creditCode = UUID.fromString("d75405e8-65cd-4bae-a0dc-1ab890cb80d6")
    )
    val creditUpdateReturn  = Credit(
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
            status = Status.APPROVED,
            numberOfInstallments = 2,
            dayFirstInstallment = Date().time.plus(10L),
            creditCode = UUID.fromString("d75405e8-65cd-4bae-a0dc-1ab890cb80d6")
    )

    val  creditDto=  CreditDTO(
        creditValue = 1234.3.toBigDecimal(),
        customerId = 1,
        numberOfInstallment = 2,
        dayOfInstallment = "15/08/2023",
    )
}
/*
[{"creditCode":"a75405e8-65cd-4bae-a0dc-1ab890cb80d6","creditValue":1234.3,"numberOfInstallment":2,"status":"IN_PROGRESS","dayFirstOfInstallment":1692378350057,"idCustomer":1,"id":1},{"creditCode":"c75405e8-65cd-4bae-a0dc-1ab890cb80d6","creditValue":1340.3,"numberOfInstallment":3,"status":"IN_PROGRESS","dayFirstOfInstallment":1692378350057,"idCustomer":1,"id":1},{"creditCode":"b75405e8-65cd-4bae-a0dc-1ab890cb80d6","creditValue":1234.3,"numberOfInstallment":2,"status":"IN_PROGRESS","dayFirstOfInstallment":19618,"idCustomer":1,"id":1}]*/
