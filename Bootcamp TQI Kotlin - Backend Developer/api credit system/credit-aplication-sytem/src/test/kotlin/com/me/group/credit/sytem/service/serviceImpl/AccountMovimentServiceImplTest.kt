package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.entity.Account
import com.me.group.credit.sytem.entity.AccountMovement
import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.TitulosMovimentacao
import com.me.group.credit.sytem.exeception.BusinessException
import com.me.group.credit.sytem.extension.convertDateStringToLong
import com.me.group.credit.sytem.repository.AccountMovimentRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*


@ActiveProfiles("Test")
@ExtendWith(MockKExtension::class)
class AccountMovimentServiceImplTest {

    @MockK
    lateinit var accounntMovimentRepositoryMock: AccountMovimentRepository
    @MockK
    lateinit var customerServiceImplMock: CustomerServiceImpl

    @InjectMockKs
     lateinit var accountMovimentImplMock: AccountMovimentService
     lateinit var customerMock : Customer
    @BeforeEach
    fun setUp() {
    customerMock= getCustomer()
    }

    @Test
    fun `saveAccountMoviment_mustSave movimentation by account`() {

        every { accounntMovimentRepositoryMock.save(accountMovementMock) } returns accountMovementMock
        val accountMovimentReturn =  accountMovimentImplMock.saveAccountMoviment(accountMovementMock)

        Assertions.assertThat(accountMovimentReturn).isNotNull
        Assertions.assertThat(accountMovimentReturn.dateMoviment).isEqualTo(Date().convertDateStringToLong("17/07/2023")!!)
        verify(exactly = 1){
            accounntMovimentRepositoryMock.save(accountMovementMock)
        }
    }
    @Test
    fun `getAllAccontMovimentCostumer_most return all moviments`() {
         val c = getCustomer()
          val ac = accountMock().copy(movements = mockMovimentation())

          every { accounntMovimentRepositoryMock.findAllByCustomer(1L) }.returns(mockMovimentation())

        val accountMovements = accountMovimentImplMock.getAllAccontMovimentCostumer(1)
        Assertions.assertThat(accountMovements).isNotEmpty
        Assertions.assertThat(accountMovements[0].type).isEqualTo(TitulosMovimentacao.PEDIDO_EMPRESTIMO)
        Assertions.assertThat(accountMovements).size().isEqualTo(3)
        verify (exactly = 1){ accounntMovimentRepositoryMock.findAllByCustomer(1L)  }
    }
     @Test
     fun `getAllAccontMovimentCostumer_most return BusinessExcption when not found customer id`() {
         every { accounntMovimentRepositoryMock.findAllByCustomer(2L)}.throws(BusinessException("fail to found all moviments"))

         Assertions.assertThatExceptionOfType(BusinessException::class.java).isThrownBy {
             accounntMovimentRepositoryMock.findAllByCustomer(2L)
         }.withMessage("fail to found all moviments")
     }

    @Test
    fun `findAccountMoviment_most return account Moviment by customer id  and  Id accountMoviment`() {

        every { accountMovimentImplMock.getAllAccontMovimentCostumer(1) }.returns(mockMovimentation())

        val accountMoviment = accountMovimentImplMock.findAccountMoviment(1L, 1L)

        Assertions.assertThat(accountMoviment).isNotNull
        Assertions.assertThat(accountMoviment?.type).isEqualTo(TitulosMovimentacao.PEDIDO_EMPRESTIMO)
        verify(exactly = 1){accountMovimentImplMock.getAllAccontMovimentCostumer(1L)}
    }

   @Test
   fun `findAccountMoviment_most return null when not found accountMovimentation`() {

       every { accountMovimentImplMock.getAllAccontMovimentCostumer(1) }.returns(mockMovimentation())

       val accountMoviment = accountMovimentImplMock.findAccountMoviment(1L, 5L)
       Assertions.assertThat(accountMoviment).isNull()
       verify(exactly = 1){accountMovimentImplMock.getAllAccontMovimentCostumer(1L)}
   }

      @Test
      fun `findAccountMoviment_most return BusinessExcption when account is null`() {
          every { customerServiceImplMock.findById(1L) }.throws(BusinessException("account not found"))

          Assertions.assertThatExceptionOfType(BusinessException::class.java).isThrownBy {
              customerServiceImplMock.findById(1L)
          }.withMessage("account not found")
      }

    /*@Test
    fun `enterMoney_must return entry of movement money in the account`() {
        every { customerServiceImplMock.findById(1) }.returns(getCustomer())
        every { accounntMovimentRepositoryMock.save(any()) }.returns(accountMovementMock)
        every { customerServiceImplMock.save(customerMock)}

        val enterMoney = accountMovimentImplMock.enterMoney(BigDecimal(100L), 1, TitulosMovimentacao.PEDIDO_EMPRESTIMO)
        Assertions.assertThat(enterMoney).isNotNull
        Assertions.assertThat(enterMoney.customer.account.accountBalanceBlocked).isEqualTo(BigDecimal.valueOf(100))

    }*/

    @AfterEach
    fun tearDown() {
    }


    fun mockMovimentation():MutableList<AccountMovement> = mutableListOf(
        AccountMovement(
            id = 1L,
            customer =getCustomer(),
            dateMoviment = Date().convertDateStringToLong("17/07/2023")!!,
            movimentValue = BigDecimal.valueOf(100L),
            type = TitulosMovimentacao.PEDIDO_EMPRESTIMO,
        ),
        AccountMovement(
            id = 2L,
            customer= getCustomer(),
            dateMoviment = Date().convertDateStringToLong("17/07/2023")!!,
            movimentValue = BigDecimal.valueOf(100L),
            type = TitulosMovimentacao.PAGAMENTO_BOLETO,
        ),

        AccountMovement(
            id = 3L,
            customer=getCustomer(),
            dateMoviment = Date().convertDateStringToLong("17/07/2023")!!,
            movimentValue = BigDecimal.valueOf(100L),
            type = TitulosMovimentacao.TED,
        )
    )

   private fun accountMock():Account = Account(
            numberAccount = Random().nextLong(100000,999999),
            accountBalanceBlocked = "100".toBigDecimal(),
            movements = mutableListOf(),
            accountFreeBalance ="200".toBigDecimal()
        )

    private  fun getCustomer():Customer {
         return Customer(
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
             account = accountMock(),
             id = 1
         )
    }


    private  val accountMovementMock = AccountMovement(
        id = 1L,
        customer = getCustomer(),
        dateMoviment = Date().convertDateStringToLong("17/07/2023")!!,
        movimentValue = BigDecimal.valueOf(100L),
        type = TitulosMovimentacao.PEDIDO_EMPRESTIMO,
    )


}