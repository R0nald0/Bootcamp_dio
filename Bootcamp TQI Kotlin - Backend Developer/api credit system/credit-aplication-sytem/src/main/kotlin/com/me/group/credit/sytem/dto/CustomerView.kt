package com.me.group.credit.sytem.dto

import com.me.group.credit.sytem.entity.Customer
import java.math.BigDecimal

data class CustomerView (
      val fistName:String,
      val lastName:String,
      val cpf :String,
      val income :BigDecimal,
      val email: String,
      val zipCode:String,
      val street :String
) {
    constructor(customer: Customer): this(
        fistName = customer.fistName,
         lastName= customer.lastName,
            cpf= customer.cpf,
            income = customer.income,
            email = customer.email,
            zipCode= customer.address.zipCode,
            street = customer.address.street
        )
}
