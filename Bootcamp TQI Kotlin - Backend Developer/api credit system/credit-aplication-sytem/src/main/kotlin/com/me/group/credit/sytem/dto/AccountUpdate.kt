package com.me.group.credit.sytem.dto

import java.math.BigDecimal

data class AccountUpdate(
    val accountFreeBalance: BigDecimal ,
    val  accountBalanceBlocked :BigDecimal,
)
