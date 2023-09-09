package com.me.group.credit.sytem.entity

import com.me.group.credit.sytem.enums.MovimentationType
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "account_movement")
data class AccountMovement(
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? =null,
        @ManyToOne
    val customer: Customer,
        val dateMoviment : Long,
        val type :MovimentationType,
        val movimentValue :BigDecimal
)