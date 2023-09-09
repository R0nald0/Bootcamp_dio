package com.me.group.credit.sytem.service

import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.Status
import com.me.group.credit.sytem.enums.MovimentationType
import java.math.BigDecimal

interface ICustomerService {
    fun save(customer: Customer):Customer
    fun findCustomerByEmail(email:String):Customer
    fun findById(idCostumer :Long):Customer
    fun getCustomerByAccountNumber(accountNumber: Long):Customer
    fun delete (idCostumer : Long):Boolean
   /* fun enterMoney(valorEntrada: BigDecimal,  idcustomer: Long, typrEntry : TitulosMovimentacao): Account*/
    fun upadateAccount(valorEntrada: BigDecimal, customer: Customer, typrEntry : MovimentationType):Customer
    fun upadateStateAccount(creditValue:BigDecimal,customer: Customer,status: Status):Customer

}