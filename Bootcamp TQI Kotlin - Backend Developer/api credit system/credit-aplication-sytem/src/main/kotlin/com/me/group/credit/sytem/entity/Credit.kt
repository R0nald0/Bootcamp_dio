package com.me.group.credit.sytem.entity

import com.me.group.credit.sytem.enums.Status
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name ="Credit")
data class Credit(
        @Column(unique = true, nullable = false)
        var creditCode : UUID = UUID.randomUUID(),

        @Column(nullable = false)
        var creditValue : BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false)
        var dayFirstInstallment :Long,
        @Column(nullable = false)
        val numberOfInstallments : Int = 0,
        @Enumerated
        var status : Status = Status.IN_PROGRESS,

        @ManyToOne
        var customer : Customer? = null,
        @Id
        @GeneratedValue(strategy =GenerationType.IDENTITY)
        val id :Long? = null

) {

}
