package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.Status
import com.me.group.credit.sytem.exeception.BusinessException
import com.me.group.credit.sytem.repository.CreditRepository
import com.me.group.credit.sytem.repository.CustomerRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceImplTest {

    @MockK
    lateinit var  repositoryCustomer : CustomerRepository
    @MockK
    lateinit var  creditRepository: CreditRepository

    @InjectMockKs
    lateinit var customerServiceImpl: CustomerServiceImpl
    @BeforeEach
    fun setUp() {
       //  customerServiceImpl= CustomerServiceImpl(repositoryCustomer,creditRepository)
    }

    @Test
    fun `save must return a Customer`(){
        every { repositoryCustomer.save(any()) }returns customer

        val customerReturn = customerServiceImpl.save(customer)
        Assertions.assertThat(customerReturn).isNotNull
        Assertions.assertThat(customerReturn.fistName).isEqualTo("Miau")
        Assertions.assertThat(customerReturn).isSameAs(customer)
        verify(exactly = 1){repositoryCustomer.save(customer)}
    }
    @Test
    fun `findById  must find customer by id and return`(){
        every { repositoryCustomer.findById(1) }returns Optional.of(customer)

        val optionalCustumer = customerServiceImpl.findById(1)
        Assertions.assertThat(optionalCustumer).isNotNull()
        Assertions.assertThat(optionalCustumer).isSameAs(customer)
        Assertions.assertThat(optionalCustumer).isInstanceOf(Customer::class.java)
        Assertions.assertThat(optionalCustumer.id).isEqualTo(1)
        verify(exactly = 1) { repositoryCustomer.findById(1) }
    }

    @Test
    fun `findById must throw BusinessException when id invalid`(){
        every { repositoryCustomer.findById(1) }returns Optional.empty()

        Assertions.assertThatExceptionOfType(BusinessException::class.java).isThrownBy {
            customerServiceImpl.findById(1)
        }.withMessage("id 1 not found")
        verify(exactly = 1) { repositoryCustomer.findById(1) }
    }



@Test
fun `delete must delete customer by id`(){
       every { creditRepository.findAllByCustomer(1) } returns listCredit
       every { repositoryCustomer.findById(1) }returns Optional.of(customer)
       every {repositoryCustomer.delete(customer) } just runs

     val resul = customerServiceImpl.delete(1)
     Assertions.assertThat(resul).isTrue()

    verify(exactly = 1) { repositoryCustomer.findById(1) }
    verify(exactly = 1) { creditRepository.findAllByCustomer(1) }
    verify(exactly = 1) { repositoryCustomer.delete(customer) }
}

@Test
fun `delete must throw a Businesse exception when credit have status approved`(){
    every { creditRepository.findAllByCustomer(1) } returns listCreditApproved
    every { repositoryCustomer.findById(1) }returns Optional.of(customer)
    every {repositoryCustomer.delete(customer) } just runs

    //val resul = customerServiceImpl.delete(1)
    Assertions.assertThatExceptionOfType(BusinessException::class.java).isThrownBy {
         customerServiceImpl.delete(1)
    }.withMessage("Customer ${customer.fistName} have pending Crédit")
    verify (exactly = 1) {repositoryCustomer.findById(1)}
    verify (exactly = 1) {creditRepository.findAllByCustomer(1)}


}
    @AfterEach
    fun tearDown() {
    }
    val customer = Customer(
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
                    customer = customer, id = 0,
                    status = Status.IN_PROGRESS,
                    numberOfInstallments = 2,
                    dayFirstInstallment = LocalDate.now(),
                    creditCode = UUID.randomUUID()
            ),
            Credit(
                    creditValue = 1340.3.toBigDecimal(),
                    customer = customer, id = 0,
                    status = Status.IN_PROGRESS,
                    numberOfInstallments = 3,
                    dayFirstInstallment = LocalDate.now(),
                    creditCode = UUID.randomUUID()
            ),
            Credit(
                    creditValue = 4990.3.toBigDecimal(),
                    customer = customer, id = 0,
                    status = Status.IN_PROGRESS,
                    numberOfInstallments = 3,
                    dayFirstInstallment = LocalDate.now(),
                    creditCode = UUID.randomUUID()
            )
    )
    val listCreditApproved = listOf(
            Credit(
                    creditValue = 1234.3.toBigDecimal(),
                    customer = customer, id = 0,
                    status = Status.APPROVED,
                    numberOfInstallments = 2,
                    dayFirstInstallment = LocalDate.now(),
                    creditCode = UUID.randomUUID()
            ),
            Credit(
                    creditValue = 1340.3.toBigDecimal(),
                    customer = customer, id = 0,
                    status = Status.IN_PROGRESS,
                    numberOfInstallments = 3,
                    dayFirstInstallment = LocalDate.now(),
                    creditCode = UUID.randomUUID()
            ),
            Credit(
                    creditValue = 4990.3.toBigDecimal(),
                    customer = customer, id = 0,
                    status = Status.IN_PROGRESS,
                    numberOfInstallments = 3,
                    dayFirstInstallment = LocalDate.now(),
                    creditCode = UUID.randomUUID()
            )
    )
}
