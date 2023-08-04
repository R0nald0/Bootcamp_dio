package com.me.group.credit.sytem.entity

import com.me.group.credit.sytem.enums.TitulosMovimentacao
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
    val type :TitulosMovimentacao,
    val movimentValue :BigDecimal
)