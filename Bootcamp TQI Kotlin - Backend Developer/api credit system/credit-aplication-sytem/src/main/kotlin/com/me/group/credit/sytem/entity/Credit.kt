package com.me.group.credit.sytem.entity

import com.me.group.credit.sytem.enums.Status
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name ="Credit")
data class Credit(
        @Column(unique = true, nullable = false)
        val creditCode : UUID = UUID.randomUUID(),

        @Column(nullable = false)
        var creditValue : BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false)
        var dayFirstInstallment :LocalDate,
        @Column(nullable = false)
        val numberOfInstallments : Int = 0,
        @Enumerated
        val status : Status = Status.IN_PROGRESS,
        @ManyToOne
        val costumer : Costumer? = null,
        @Id
        @GeneratedValue(strategy =GenerationType.IDENTITY)
        val id :Long? = null

) {

}