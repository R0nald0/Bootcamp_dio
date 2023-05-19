package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.enums.Status
import com.me.group.credit.sytem.exeception.BusinessException
import com.me.group.credit.sytem.repository.CreditRepository
import com.me.group.credit.sytem.service.ICreditService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.util.*

@Service
class CreditService(
        private val creditRepository : CreditRepository,
        private val costumerService : CustomerServiceImpl
) :ICreditService {
    override fun save(credit: Credit): Credit {

        val data = credit.dayFirstInstallment
        val dataLimit = LocalDate.now().plusMonths(3L)
        if (data <= dataLimit){
            credit.apply {
                this.customer = costumerService.findById(credit.customer?.id!!)
            }
          val cre = credit.copy(status = Status.APPROVED)
            return  creditRepository.save(cre)
        }else{
            throw BusinessException("the date must be on maximum three month forward")
        }
    }

    override fun findAllByCostumer(idCustomer: Long): List<Credit> {
        return creditRepository.findAllByCustomer(idCustomer)
    }

    override fun findByCreditCode(uuid: UUID, idCustomer: Long): Credit {
       val credit = creditRepository.findByCreditCode(uuid)
                ?:throw BusinessException("Credit code $uuid not found")
        return if (credit.customer?.id == idCustomer)  credit
                  else throw IllegalArgumentException("credit is not found for this User")
    }
}