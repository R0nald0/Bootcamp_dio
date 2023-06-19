package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.Status
import com.me.group.credit.sytem.exeception.BusinessException
import com.me.group.credit.sytem.repository.CreditRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {

    @MockK
    lateinit var creditiRepostiroryMock : CreditRepository
    @MockK
    lateinit var costumerServiceMock : CustomerServiceImpl

    @InjectMockKs
    lateinit var  creditService: CreditService
    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `save_must create a credit and return creditCreated`() {
        val credit = getCredit()
        val customerMock = getCustomer()
        every { costumerServiceMock.findById(1) }returns customerMock
        every { creditiRepostiroryMock.save(credit) }returns credit

        val creditCreatedMock = creditService.save(credit)
        Assertions.assertThat(creditCreatedMock.id).isEqualTo(1)
         Assertions.assertThat(creditCreatedMock).isSameAs(credit)
        Assertions.assertThat(creditCreatedMock).isInstanceOf(Credit::class.java)
         verify (exactly = 1){ creditiRepostiroryMock.save(credit)  }
       Assertions.assertThat(creditCreatedMock.customer).isNotNull()
    }

    @Test
    fun `save_must return BusinessExceptiona when date invalid`() {
        val creditMock = getCredit().
        copy(dayFirstInstallment = LocalDate.of(2023,10,14))

        every { creditiRepostiroryMock.save(any())}returns creditMock

        Assertions.assertThatExceptionOfType(BusinessException::class.java).isThrownBy {
            creditService.save(creditMock)
        }.withMessage("the date must be on maximum three month forward")
    }

    @Test
    fun `findCreditByCode_must return a credit by your code`() {
          val credit = getCredit()

         every { creditiRepostiroryMock.findByCreditCode(creditCode = credit.creditCode) }returns  credit
         val resultCredit = creditService.findByCreditCode(credit.creditCode,1)

        Assertions.assertThat(resultCredit).isNotNull
        Assertions.assertThat(resultCredit.customer?.fistName).isEqualTo("Miau")
        Assertions.assertThat(resultCredit.id).isEqualTo(1)
        verify(exactly = 1){creditiRepostiroryMock.findByCreditCode(credit.creditCode)}
    }

    @Test
    fun `findCreditByCode_should return IllegalArgumentException when not found credit from customer`() {
        every { creditiRepostiroryMock.findByCreditCode(any()) }returns getCredit()

        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            creditService.findByCreditCode(UUID.randomUUID(),2)
        }.withMessage("credit is not found for this User")
        verify(exactly = 1){creditiRepostiroryMock.findByCreditCode(any()) }
    }

    @Test
    fun `findAllByCustomer_should return all credits from a customer`() {

        every { creditiRepostiroryMock.findAllByCustomer(1L) } returns listCredit

        val resultCredits = creditService.findAllByCostumer(1)
        Assertions.assertThat(resultCredits).isNotEmpty
        Assertions.assertThat(resultCredits[0].creditValue).isEqualTo(1234.3.toBigDecimal())
    }

    @Test
    fun `findCreditByCode_should return BusinessException when not find credit id`() {
        val creditCode = UUID.randomUUID()
        every { creditiRepostiroryMock.findByCreditCode(creditCode) }returns null

        Assertions.assertThatExceptionOfType(BusinessException::class.java).isThrownBy {
            creditService.findByCreditCode(creditCode,2)
        }.withMessage("Credit code ${creditCode} not found")
       verify(exactly = 1){creditiRepostiroryMock.findByCreditCode(any()) }
    }

    @AfterEach
    fun tearDown() {
    }companion object {
        private fun buildCredit(
            creditValue: BigDecimal = BigDecimal.valueOf(100.0),
            dayFirstInstallment: LocalDate = LocalDate.now().plusMonths(2L),
            numberOfInstallments: Int = 15,
            customer: Customer = CustomerServiceImplTest().customer
        ): Credit = Credit(
            creditValue = creditValue,
            dayFirstInstallment = dayFirstInstallment,
            numberOfInstallments = numberOfInstallments,
            customer = customer
        )
    }
    fun getCustomer() = Customer(
        "Miau",
        "Silva",
        "03656811806",
        3000.0.toBigDecimal(),
        "miau@email.com",
        "21313",
        Address(
            zipCode = "321344", street = "rua do peixe"
        ),
        mutableListOf(),1,
    )
    val listCredit = listOf(
        Credit(
            creditValue = 1234.3.toBigDecimal(),
            customer = getCustomer(), id = 0,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 2,
            dayFirstInstallment = LocalDate.now(),
            creditCode = UUID.randomUUID()
        ),
        Credit(
            creditValue = 1340.3.toBigDecimal(),
            customer = getCustomer(), id = 0,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 3,
            dayFirstInstallment = LocalDate.now(),
            creditCode = UUID.randomUUID()
        ),
        Credit(
            creditValue = 4990.3.toBigDecimal(),
            customer = getCustomer(), id = 0,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 3,
            dayFirstInstallment = LocalDate.now(),
            creditCode = UUID.randomUUID()
        )
    )

    fun getCredit() =  Credit(
        creditValue = 1234.3.toBigDecimal(),
        customer = getCustomer() ,
        id = 1,
        status = Status.IN_PROGRESS,
        numberOfInstallments = 2,
        dayFirstInstallment = LocalDate.now().plusMonths(1),
        creditCode = UUID.randomUUID()
    )
}

