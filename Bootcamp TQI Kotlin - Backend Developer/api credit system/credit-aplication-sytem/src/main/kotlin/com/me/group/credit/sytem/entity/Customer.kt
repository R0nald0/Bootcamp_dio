package com.me.group.credit.sytem.entity

import com.me.group.credit.sytem.dto.CostumerDTO
import com.me.group.credit.sytem.dto.CustomerView
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name ="Costumer")
data class Customer(
        @Column(nullable = false)
        var fistName :String = "",
        @Column(nullable = false)
       var lastName :String = "",
        @Column(nullable = false, unique = true)
        val cpf :String = "",
         @Column(nullable = false)
        var income : BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false, unique = true)
       var email :String = "",
        @Column(nullable = false)
        var password :String = "",
        @Column(nullable = false)
       @Embedded
        var address :Address = Address(),
        @Column(nullable = false)
       @OneToMany(
               fetch = FetchType.LAZY,
               cascade =[CascadeType.REMOVE,CascadeType.PERSIST],
               mappedBy = "customer"
               )
       var credits : List<Credit> = mutableListOf(),

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long ? = null
)

fun Customer.toView() = CustomerView(
        fistName = this.fistName,
        lastName = this.lastName,
        cpf = this.cpf,
        street = this.address.street,
        income = this.income,
        email = this.email,
        zipCode = this.address.zipCode
)