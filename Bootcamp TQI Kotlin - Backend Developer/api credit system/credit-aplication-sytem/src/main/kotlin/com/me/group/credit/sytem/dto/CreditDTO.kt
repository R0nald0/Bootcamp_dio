package com.me.group.credit.sytem.dto

import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDTO(
        @field:NotNull
        val creditValue: BigDecimal,
        @field:Future(message = "this date is on past")
        val dayOfInstallment:LocalDate,
        @field:Min(value = 1, message = "Number Minimum of installment is invalid")
        @field:Max(value = 24, message = "Number maximum of installment is invalid")
        val numberOfInstallment : Int,
        @field:NotNull
        val customerId :Long
)
fun CreditDTO.toEntity()=Credit(
        dayFirstInstallment = this.dayOfInstallment,
        creditValue = this.creditValue,
        numberOfInstallments = this.numberOfInstallment,
        customer = Customer(id = this.customerId)
)
