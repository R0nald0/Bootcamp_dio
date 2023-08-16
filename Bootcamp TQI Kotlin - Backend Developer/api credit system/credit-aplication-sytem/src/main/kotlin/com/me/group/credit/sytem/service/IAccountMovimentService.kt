package com.me.group.credit.sytem.service

import com.me.group.credit.sytem.dto.AccountMovimentDTO
import com.me.group.credit.sytem.entity.AccountMovement

interface IAccountMovimentService  {
    fun saveAccountMoviment(accountMovimentDto: AccountMovimentDTO):AccountMovement
    fun getAllAccontMovimentCostumer(idCostumer : Long):List<AccountMovement>
    fun findAccountMoviment(idAccountMovement: Long,idCustomer:Long):AccountMovement?
    /*fun enterMoney(valorEntrada: BigDecimal, idcustomer: Long, typrEntry : TitulosMovimentacao): AccountMovement*/
}