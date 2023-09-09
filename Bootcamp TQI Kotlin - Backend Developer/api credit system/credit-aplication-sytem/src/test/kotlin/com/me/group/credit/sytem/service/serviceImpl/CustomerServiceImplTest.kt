package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.entity.*
import com.me.group.credit.sytem.enums.Status
import com.me.group.credit.sytem.enums.MovimentationType
import com.me.group.credit.sytem.exeception.BusinessException
import com.me.group.credit.sytem.extension.convertDateStringToLong
import com.me.group.credit.sytem.repository.AccountMovimentRepository
import com.me.group.credit.sytem.repository.CreditRepository
import com.me.group.credit.sytem.repository.CustomerRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class  CustomerServiceImplTest {

    @MockK
    lateinit var  repositoryCustomer : CustomerRepository
    @MockK
    lateinit var  creditRepository: CreditRepository
     @MockK
     lateinit var  accountMovimentRepository: AccountMovimentRepository

    @InjectMockKs
    lateinit var customerServiceImplMock: CustomerServiceImpl
    @BeforeEach
    fun setUp() {
       //  customerServiceImpl= CustomerServiceImpl(repositoryCustomer,creditRepository)
    }

    @Test
    fun `save must return a Customer`(){

        every { repositoryCustomer.save(any())}returns customer
        val customerReturn = customerServiceImplMock.save(customer)

        Assertions.assertThat(customerReturn).isNotNull
        Assertions.assertThat(customerReturn.fistName).isEqualTo("Miau")
        Assertions.assertThat(customerReturn).isSameAs(customer)
        Assertions.assertThat(customerReturn.account.accountBalanceBlocked).isEqualTo("0".toBigDecimal())
        verify(exactly = 1){repositoryCustomer.save(customer)}
    }
    @Test
    fun `findById  must find customer by id and return`(){
        every { repositoryCustomer.findById(1) }returns Optional.of(customer)

        val optionalCustumer = customerServiceImplMock.findById(1)
        Assertions.assertThat(optionalCustumer).isNotNull()
        Assertions.assertThat(optionalCustumer).isSameAs(customer)
        Assertions.assertThat(optionalCustumer).isInstanceOf(Customer::class.java)
        Assertions.assertThat(optionalCustumer.account.numberAccount).isEqualTo(999999)
        Assertions.assertThat(optionalCustumer.id).isEqualTo(1)

        verify(exactly = 1) { repositoryCustomer.findById(1)

        }
    }

    @Test
    fun `getCustomerByAccountNumber_should return customer by your account number`() {
          every { repositoryCustomer.findByAccount(any())  } returns (customer)

        //when
        val customerByAccountNumber = customerServiceImplMock.getCustomerByAccountNumber(999999)
        Assertions.assertThat(customerByAccountNumber.fistName).isEqualTo("Miau")
        Assertions.assertThat(customerByAccountNumber.email).isEqualTo("miau@email.com")
        Assertions.assertThat(customerByAccountNumber.cpf).isEqualTo("03656811806")

        verify(exactly = 1){repositoryCustomer.findByAccount(any())}


        //TODO see teste error

    }

    @Test
    fun `findById must throw BusinessException when id invalid`(){
        every { repositoryCustomer.findById(1) }returns Optional.empty()

        Assertions.assertThatExceptionOfType(BusinessException::class.java).isThrownBy {
            customerServiceImplMock.findById(1)
        }.withMessage("id 1 not found")
        verify(exactly = 1) { repositoryCustomer.findById(1) }
    }
@Test
fun `delete must delete customer by id`(){
       every { creditRepository.findAllByCustomer(1) } returns listCredit
       every { repositoryCustomer.findById(1) }returns Optional.of(customer)
       every {repositoryCustomer.delete(customer) } just runs

     val resul = customerServiceImplMock.delete(1)
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

    Assertions.assertThatExceptionOfType(BusinessException::class.java).isThrownBy {
         customerServiceImplMock.delete(1)
    }.withMessage("Customer ${customer.fistName} have pending Crédit")
    verify (exactly = 1) {repositoryCustomer.findById(1)}
    verify (exactly = 1) {creditRepository.findAllByCustomer(1)}
}

    @Test
    fun `findCustomerByEmail_most return a custmer by email`() {
         val email= "miau@email.com"
          every { repositoryCustomer.findByEmail(email) } returns customer

       val customerResultMock =   customerServiceImplMock.findCustomerByEmail(email)
        Assertions.assertThat(customerResultMock).isNotNull
        Assertions.assertThat(customerResultMock.fistName).isEqualTo("Miau")
        Assertions.assertThat(customerResultMock).isInstanceOf(Customer::class.java)
        Assertions.assertThat(customerResultMock.account.numberAccount).isEqualTo(999999)
        Assertions.assertThat(customerResultMock).isSameAs(customer)
        verify(exactly = 1){
            repositoryCustomer.findByEmail(email)
        }
    }

    @Test
    fun `findCustomerByEmail_most return BusinessException when not found email`() {
        val email = "fakeemail@Gmilcom"
         every { repositoryCustomer.findByEmail(email) } returns null

       Assertions.assertThatExceptionOfType(BusinessException::class.java).isThrownBy {
           customerServiceImplMock.findCustomerByEmail(email)
       }.withMessage("customer not find by email: $email")
        verify(exactly = 1 ){repositoryCustomer.findByEmail(email)}
    }

    @Test
    fun `upadateAccount_must upadate account`() {
         every { repositoryCustomer.save(any()) }.returns(customer)

        val upadateAccount = customerServiceImplMock.upadateAccount(
            BigDecimal.valueOf(200), customer, MovimentationType.PEDIDO_EMPRESTIMO
        )
        Assertions.assertThat(upadateAccount.account.accountBalanceBlocked).isEqualTo(BigDecimal.valueOf(200))
        Assertions.assertThat(upadateAccount.account.accountFreeBalance).isEqualTo(BigDecimal.valueOf(0))
        verify(exactly = 1){ repositoryCustomer.save(any())}

    }
    @Test
     fun `upadateStateAccount_must return customer with account balances upadted`(){
           every { repositoryCustomer.save(any()) }.returns(customer)

           val upadteCus = customerServiceImplMock.upadateStateAccount(BigDecimal.valueOf(100.00),customer,Status.APPROVED)

        Assertions.assertThat(upadteCus.account.accountFreeBalance).isEqualTo("100.0")
        Assertions.assertThat(upadteCus.account.accountBalanceBlocked).isEqualTo("0")
        Assertions.assertThat(upadteCus.account.numberAccount).isEqualTo("999999".toLong())

     }

    @AfterEach
    fun tearDown() {}

     fun getAccount() :Account{
         return  Account(
             numberAccount =999999,
             accountBalanceBlocked = BigDecimal.valueOf(0),
             movements = mutableListOf(),
             accountFreeBalance = BigDecimal.valueOf(100.00)
         )
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
        mutableListOf(),
        account = getAccount(),
        id = 1
    )

    val accountMoviment = AccountMovement(
        dateMoviment = Date().time,
        type = MovimentationType.PEDIDO_EMPRESTIMO,
        id = 1,
        movimentValue = "1300".toBigDecimal(),
        customer = customer
    )

    val litAccountMovimetn = mutableListOf(
        accountMoviment
    )
    val listCredit = listOf(
        Credit(
            creditValue = 1234.3.toBigDecimal(),
            customer = customer, id = 0,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 2,
            dayFirstInstallment =Date().convertDateStringToLong("17/07/2023")!!,
            creditCode = UUID.randomUUID()
        ),
        Credit(
            creditValue = 1340.3.toBigDecimal(),
            customer = customer, id = 0,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 3,
            dayFirstInstallment = Date().convertDateStringToLong("17/07/2023")!!,
            creditCode = UUID.randomUUID()
        ),
        Credit(
            creditValue = 4990.3.toBigDecimal(),
            customer = customer, id = 0,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 3,
            dayFirstInstallment = Date().convertDateStringToLong("17/07/2023")!! ,
            creditCode = UUID.randomUUID()
        )
    )
    val listCreditApproved = listOf(
        Credit(
            creditValue = 1234.3.toBigDecimal(),
            customer = customer, id = 0,
            status = Status.APPROVED,
            numberOfInstallments = 2,
            dayFirstInstallment = Date().convertDateStringToLong("17/07/2023")!!,
            creditCode = UUID.randomUUID()
        ),
        Credit(
            creditValue = 1340.3.toBigDecimal(),
            customer = customer, id = 0,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 3,
            dayFirstInstallment =Date().convertDateStringToLong("17/07/2023")!!,
            creditCode = UUID.randomUUID()
        ),
        Credit(
            creditValue = 4990.3.toBigDecimal(),
            customer = customer, id = 0,
            status = Status.IN_PROGRESS,
            numberOfInstallments = 3,
            dayFirstInstallment =Date().convertDateStringToLong("17/07/2023")!!,
            creditCode = UUID.randomUUID()
        )
    )

}

