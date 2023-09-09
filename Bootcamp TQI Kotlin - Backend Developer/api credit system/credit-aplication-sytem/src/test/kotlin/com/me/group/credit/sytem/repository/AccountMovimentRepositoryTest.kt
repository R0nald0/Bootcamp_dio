package com.me.group.credit.sytem.repository

import com.me.group.credit.sytem.entity.Account
import com.me.group.credit.sytem.entity.AccountMovement
import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.MovimentationType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

@ActiveProfiles("Test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountMovimentRepositoryTest {
    @Autowired
    lateinit var accountMovimentRepository: AccountMovimentRepository
    @Autowired
    lateinit var  testEntityManager: TestEntityManager




    lateinit var customer: Customer
    lateinit var accountMovementPersit : AccountMovement
    lateinit var accountMovementPersit1 : AccountMovement

    @BeforeEach
    fun setUp() {
        customer =testEntityManager.persist(buildCustomer())
        accountMovementPersit = testEntityManager.persist(accountMovement1())
        accountMovementPersit1 = testEntityManager.persist(getAccountMovement())
    }

    @Test
    fun `findAllByCustomer_must return list of accountMoviment by customer`() {
        val listaAccountMovement  = accountMovimentRepository.findAllByCustomer(1)

        Assertions.assertThat(listaAccountMovement).isNotEmpty
        Assertions.assertThat(listaAccountMovement).hasSize(2)
        Assertions.assertThat(listaAccountMovement[0].type).isEqualTo(MovimentationType.TED)
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

  fun accountMovement1() = AccountMovement(
    dateMoviment = Date().time,
    type = MovimentationType.TED,
    movimentValue = "1230.0".toBigDecimal(),
    customer = customer
    )
     fun getAccountMovement() = AccountMovement(
         dateMoviment = Date().time,
         type = MovimentationType.PIX,
         movimentValue = "3230.0".toBigDecimal(),
        customer = customer
     )

      /*  listOf(

        AccountMovement(
            id =1,
            dateMoviment = Date().time,
            type = TitulosMovimentacao.PEDIDO_EMPRESTIMO,
            movimentValue = "5432.0".toBigDecimal(),
            customer = buildCustomer()
        ),
        AccountMovement(
            id =1,
            dateMoviment = Date().time,
            type = TitulosMovimentacao.PAGAMENTO_BOLETO,
            movimentValue = "45.0".toBigDecimal(),
            customer = buildCustomer()
        ),
    )*/
}