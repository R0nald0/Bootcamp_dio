package com.me.group.credit.sytem.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Embeddable
data class Account(

    @Column(name="account_free_balance",)
    var accountFreeBalance: BigDecimal = BigDecimal.ZERO,

    @Column(name="balance_blocked")
    var accountBalanceBlocked :BigDecimal = BigDecimal.ZERO,

    val numberAccount:Long = Random().nextLong(100000,999999),

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        mappedBy = "customer"
    )
   var movements :MutableList<AccountMovement> = mutableListOf()
)
