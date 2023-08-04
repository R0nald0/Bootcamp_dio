package com.me.group.credit.sytem.repository

import com.me.group.credit.sytem.entity.Account
import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Customer
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


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class CustomerRepositoryTest {

    @Autowired
    lateinit var customerRepositoryMock:CustomerRepository
    @Autowired
    lateinit var testEntityManager: TestEntityManager

    lateinit var  customerMock : Customer
    @BeforeEach
    fun setUp(){
       customerMock = testEntityManager.persist(buildCustomer())
    }

    @Test
    fun `findByEmail_should return a customer by your email`() {
      val resultCustomer  =  customerRepositoryMock.findByEmail(customerMock.email)
       Assertions.assertThat(resultCustomer?.fistName).isEqualTo("Miau")
       Assertions.assertThat(resultCustomer).isSameAs(customerMock)

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
         account = Account(
               numberAccount = Random().nextLong(100000,999999),
               accountBalanceBlocked = BigDecimal.valueOf(2000L),
               movements = mutableListOf(),
              accountFreeBalance = BigDecimal.valueOf(3000L)
    )
        )
}
