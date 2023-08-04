package com.me.group.credit.sytem.repository

import com.me.group.credit.sytem.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CreditRepository :JpaRepository<Credit,Long> {
   fun findByCreditCode(creditCode : UUID):Credit?

   @Query(value = "SELECT *FROM Credit WHERE customer_id = ?1", nativeQuery = true)
    fun findAllByCustomer(idCustomer:Long):List<Credit>
}