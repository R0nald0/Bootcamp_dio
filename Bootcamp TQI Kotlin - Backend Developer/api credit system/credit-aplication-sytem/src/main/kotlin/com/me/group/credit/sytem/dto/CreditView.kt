package com.me.group.credit.sytem.dto

import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.enums.Status

import java.math.BigDecimal
import java.util.UUID

data class CreditView(
        var creditCode :UUID,
        var creditValue: BigDecimal,
        var numberOfInstallment : Int,
        var status: Status,
        var emailCustomer :String?,
        var incomeCustomer :BigDecimal?
) {
    constructor(credit: Credit) : this(
            creditValue = credit.creditValue,
            creditCode = credit.creditCode,
            numberOfInstallment = credit.numberOfInstallments,
            status = credit.status,
            emailCustomer = credit.customer?.email,
            incomeCustomer = credit.customer?.income
    )
}
