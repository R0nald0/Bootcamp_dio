package com.me.group.credit.sytem.service

import com.me.group.credit.sytem.entity.AccountMovement

interface IAccountMovimentService  {
    fun saveAccountMoviment(accountMoviment: AccountMovement):AccountMovement
    fun getAllAccontMovimentCostumer(idCostumer : Long):List<AccountMovement>
    fun findAccountMoviment(idAccountMovement: Long,idCustomer:Long):AccountMovement?
    /*fun enterMoney(valorEntrada: BigDecimal, idcustomer: Long, typrEntry : TitulosMovimentacao): AccountMovement*/
}