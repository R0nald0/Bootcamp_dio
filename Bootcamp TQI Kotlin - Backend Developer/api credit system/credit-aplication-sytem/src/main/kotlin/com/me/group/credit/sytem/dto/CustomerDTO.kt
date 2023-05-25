package com.me.group.credit.sytem.dto

import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CostumerDTO (
        @field:NotEmpty(message="fistName can´t be null")
        val fistName :String,
        @field:NotEmpty(message="lastName can´t be null")
        val lastName :String,
        @field:CPF(message = "cpf is invalid")
        val  cpf :String,
        @field:NotNull
        val income :BigDecimal,
        @field:Email
        @NotEmpty
        val email :String,
        @field:NotEmpty
        val passoword :String,
        @field:NotEmpty
        val zipCode :String,
        @field:NotEmpty
        val street :String,
        )

fun CostumerDTO.toEnttity() = Customer(
 fistName = this.fistName,
        lastName = this.lastName,
        cpf = this.cpf,
        income =  this.income,
        email = this.email,
        password = this.passoword,
        address = Address(
                zipCode =this.zipCode,
                street = this.street
        ),

)
