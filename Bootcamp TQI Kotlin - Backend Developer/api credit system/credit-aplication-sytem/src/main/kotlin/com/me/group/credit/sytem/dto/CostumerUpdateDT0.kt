package com.me.group.credit.sytem.dto

import com.me.group.credit.sytem.entity.Customer
import java.math.BigDecimal

data class CostumerUpdateDT0(
          val fistName :String,
          val lastName :String,
         val  income :BigDecimal,
         val zipCode :String,
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
