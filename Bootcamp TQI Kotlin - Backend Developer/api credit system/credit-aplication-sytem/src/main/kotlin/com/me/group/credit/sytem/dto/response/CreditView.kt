package com.me.group.credit.sytem.dto.response

import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.enums.Status
import java.math.BigDecimal
import java.util.*

data class CreditView(
        var creditCode :UUID,
        var creditValue: BigDecimal,
        var numberOfInstallment : Int,
        var status: Status,
        val dayFirstOfInstallment : Long,
        val idCustomer :Long ,
        val id :Long?
) {
    constructor(credit: Credit) : this(
            creditValue = credit.creditValue,
            creditCode = credit.creditCode,
            numberOfInstallment = credit.numberOfInstallments,
            status = credit.status,
            dayFirstOfInstallment = credit.dayFirstInstallment,
            idCustomer = credit.customer?.id!!,
            id = credit.id
    )
}
