package com.me.group.credit.sytem.dto.response

import com.me.group.credit.sytem.entity.AccountMovement
import com.me.group.credit.sytem.enums.TitulosMovimentacao
import com.me.group.credit.sytem.extension.convertDateLongToString
import java.math.BigDecimal
import java.util.*

data class AccountMovimentView(
    val dateMoviment : String,
    val type : TitulosMovimentacao,
    val movimentValue : BigDecimal,
){
    constructor(accountMovement: AccountMovement):this(
        dateMoviment = Date().convertDateLongToString(accountMovement.dateMoviment)!!,
        movimentValue = accountMovement.movimentValue,
        type = accountMovement.type
    )
}
