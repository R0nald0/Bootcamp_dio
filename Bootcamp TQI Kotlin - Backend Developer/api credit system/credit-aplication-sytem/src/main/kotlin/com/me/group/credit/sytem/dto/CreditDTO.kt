package com.me.group.credit.sytem.dto

import com.me.group.credit.sytem.entity.Account
import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.extension.convertDateStringToLong
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.util.*

data class CreditDTO(
        @field:NotNull
        val creditValue: BigDecimal,
        @field:NotNull( message ="field not valid")
        val dayOfInstallment:String,
        @field:Min(value = 1, message = "Number Minimum of installment is invalid")
        @field:Max(value = 24, message = "Number maximum of installment is invalid")
        val numberOfInstallment : Int,
        @field:NotNull
        val customerId :Long
)
fun CreditDTO.toEntity()= Credit(
        dayFirstInstallment = Date().convertDateStringToLong(this.dayOfInstallment)!!,
        creditValue = this.creditValue,
        numberOfInstallments = this.numberOfInstallment,
        customer = Customer(id = this.customerId, account = Account())
)
