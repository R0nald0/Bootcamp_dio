package com.me.group.credit.sytem.repository

import com.me.group.credit.sytem.entity.AccountMovement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AccountMovimentRepository :JpaRepository<AccountMovement,Long> {
    @Query(value = "SELECT * FROM account_movement WHERE customer_id =?1", nativeQuery = true)
    fun findAllByCustomer(idCustomer:Long):MutableList<AccountMovement>
}