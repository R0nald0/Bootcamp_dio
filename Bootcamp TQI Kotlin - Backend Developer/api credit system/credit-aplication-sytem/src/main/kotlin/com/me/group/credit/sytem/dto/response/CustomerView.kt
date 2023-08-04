package com.me.group.credit.sytem.dto.response

import com.me.group.credit.sytem.entity.Customer
import java.math.BigDecimal

data class CustomerView (
    val fistName:String,
    val lastName:String,
    val cpf :String,
    val income :BigDecimal,
    val email: String,
    val zipCode:String,
    val street :String,
    val id: Long?,
    var accountFreeBalance: BigDecimal,
    var accountBalanceBlocked :BigDecimal ,
    val numberAccount:Long
) {
    constructor(customer: Customer): this(
        id = customer.id,
        fistName = customer.fistName,
        lastName= customer.lastName,
        cpf= customer.cpf,
        income = customer.income,
        email = customer.email,
        zipCode= customer.address.zipCode,
        street = customer.address.street,
        numberAccount = customer.account.numberAccount,
        accountBalanceBlocked = customer.account.accountBalanceBlocked,
        accountFreeBalance = customer.account.accountFreeBalance
        )
}
