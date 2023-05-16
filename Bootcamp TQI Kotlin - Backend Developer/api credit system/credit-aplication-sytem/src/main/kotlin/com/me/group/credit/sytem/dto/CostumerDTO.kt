package com.me.group.credit.sytem.dto

import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Customer
import java.math.BigDecimal

data class CostumerDTO (
        val fisteName :String,
        val lastName :String,
        val  cpf :String,
        val income :BigDecimal,
        val email :String,
        val passoword :String,
        val zipCode :String,
        val street :String,
        )

fun CostumerDTO.toEnttity() = Customer(
 fistName = this.fisteName,
        lastName = this.lastName,
        cpf = this.cpf,
        income =  this.income,
        email = this.email,
        password = this.passoword,
        address = Address(
                zipCode =this.zipCode,
                street = this.street
        )
)
