package com.me.group.credit.sytem.repository

import com.me.group.credit.sytem.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer,Long>{
   fun findByEmail(email:String):Customer?
}