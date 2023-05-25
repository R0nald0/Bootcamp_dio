package com.me.group.credit.sytem.dto

import com.me.group.credit.sytem.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CostumerUpdateDT0(
        @field:NotEmpty(message="fisteName can´t be null")
          val fistName :String,
        @field:NotEmpty(message="lastName can´t be null")
          val lastName :String,
        @field:NotNull
         val  income :BigDecimal,
        @field:NotEmpty(message="zipCode can´t be null")
         val zipCode :String,
        @field:NotEmpty(message="street can´t be null")
         val street :String,
)
fun CostumerUpdateDT0.toEntity(customer: Customer):Customer{
   customer.fistName = this.fistName
   customer.lastName = this.lastName
   customer.income =  this.income
    customer.address.street = this.street
    customer.address.zipCode = this.zipCode

    return customer
}
