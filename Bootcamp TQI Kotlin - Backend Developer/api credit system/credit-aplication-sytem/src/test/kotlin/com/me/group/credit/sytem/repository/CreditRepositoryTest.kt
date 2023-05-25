package com.me.group.credit.sytem.repository

import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.Status
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.*

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {

    @Autowired
      lateinit var creditRepository : CreditRepository

      @Autowired
     lateinit var testeEntityManager :TestEntityManager

    private lateinit var customer: Customer
    private lateinit var credit1: Credit
    private lateinit var credit2: Credit

    @BeforeEach fun setup () {
        customer = testeEntityManager.persist(buildCustomer())
        credit1 = testeEntityManager.persist(buildCredit(customer = customer))
        credit2 = testeEntityManager.persist(buildCredit(customer = customer))
    }

 @Test
    fun `findByCreditCode_must search credit by credit code`(){

       val fakeCredit = creditRepository.findByCreditCode(creditCode = credit1.creditCode)!!
        Assertions.assertThat(fakeCredit).isNotNull
        Assertions.assertThat(fakeCredit.customer?.fistName).isEqualTo("Miau")
 }

    @Test
     fun `findAllByCustomer_must list credits by id customer`(){
        val creditList  = creditRepository.findAllByCustomer(customer.id!!)

        Assertions.assertThat(creditList).isNotEmpty
        Assertions.assertThat(creditList).hasSize(2)
        Assertions.assertThat(creditList).contains(credit1,credit2)
     }

    private fun buildCredit(
            creditValue: BigDecimal = BigDecimal.valueOf(500.0),
            dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.APRIL, 22),
            numberOfInstallments: Int = 5,
            customer: Customer
    ): Credit = Credit(
            creditValue = creditValue,
            dayFirstInstallment = dayFirstInstallment,
            numberOfInstallments = numberOfInstallments,
            customer = customer
    )


}
fun buildCustomer() =Customer(
       fistName =   "Miau",lastName= "Silva",
cpf = "28475934625",
email= "miau@email.com",
password = "12345",
        address = Address(
                zipCode = "3232",
                street = " rrererr "
        ),
income= BigDecimal.valueOf(1000.0),

)

val listCredit = listOf(
        Credit(
                creditValue = 1234.3.toBigDecimal(),
                customer = buildCustomer(), id = 0,
                status = Status.IN_PROGRESS,
                numberOfInstallments = 2,
                dayFirstInstallment = LocalDate.now(),
                creditCode = UUID.randomUUID()
        ),
        Credit(
                creditValue = 1340.3.toBigDecimal(),
                customer = buildCustomer(), id = 0,
                status = Status.IN_PROGRESS,
                numberOfInstallments = 3,
                dayFirstInstallment = LocalDate.now(),
                creditCode = UUID.randomUUID()
        ),
        Credit(
                creditValue = 4990.3.toBigDecimal(),
                customer = buildCustomer(), id = 0,
                status = Status.IN_PROGRESS,
                numberOfInstallments = 3,
                dayFirstInstallment = LocalDate.now(),
                creditCode = UUID.randomUUID()
        )
)
