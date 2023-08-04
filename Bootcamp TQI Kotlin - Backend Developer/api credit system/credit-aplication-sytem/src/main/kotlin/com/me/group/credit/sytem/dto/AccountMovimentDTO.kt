package com.me.group.credit.sytem.dto

import com.me.group.credit.sytem.entity.Account
import com.me.group.credit.sytem.entity.AccountMovement
import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.TitulosMovimentacao
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.util.*

data class AccountMovimentDTO(
    @field: NotNull(message = "field is invalid")
    val idCustomer: Long,
    val dateMoviment : Long,
    @field: NotNull(message = "field is invalid")
    val type : TitulosMovimentacao,
    @field:NotNull(message = "field is invalid")
    val movimentValue : BigDecimal,
    )

fun AccountMovimentDTO.toAccountMoviment() =AccountMovement(
    dateMoviment = Date().time,
    type = this.type,
    movimentValue = this.movimentValue,
    customer = Customer(id = this.idCustomer!!, account = Account())
)
