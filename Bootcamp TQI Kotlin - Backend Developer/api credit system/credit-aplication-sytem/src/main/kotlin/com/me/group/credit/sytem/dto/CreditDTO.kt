package com.me.group.credit.sytem.dto

import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDTO(
        val creditValue: BigDecimal,
        val dayOfInstallment:LocalDate,
        val numberOfInstallment : Int,
        val customerId :Long
)
fun CreditDTO.toEntity()=Credit(
        dayFirstInstallment = this.dayOfInstallment,
        creditValue = this.creditValue,
        numberOfInstallments = this.numberOfInstallment,
        customer = Customer(id = this.customerId)
)
