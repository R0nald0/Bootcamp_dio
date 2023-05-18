package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.exeception.BusinessException
import com.me.group.credit.sytem.repository.CreditRepository
import com.me.group.credit.sytem.service.ICreditService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.*

@Service
class CreditService(
        private val creditRepository : CreditRepository,
        private val costumerService : CustomerServiceImpl
) :ICreditService {
    override fun save(credit: Credit): Credit {
          credit.apply {
              this.customer = costumerService.findById(credit.customer?.id!!)
          }
        return  creditRepository.save(credit)
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