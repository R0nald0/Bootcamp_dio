package com.me.group.credit.sytem.repository

import com.me.group.credit.sytem.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer,Long>{
   fun findByEmail(email:String):Customer?

   @Query(value = "SELECT * FROM Costumer WHERE number_account = ?1" , nativeQuery = true)
   fun findByAccount(accountNumber:Long):Customer?

}