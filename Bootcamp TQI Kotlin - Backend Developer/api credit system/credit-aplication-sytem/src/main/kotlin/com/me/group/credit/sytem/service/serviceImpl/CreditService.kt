package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.repository.CreditRepository
import com.me.group.credit.sytem.service.ICreditService
import org.springframework.stereotype.Service
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

    override fun findAllByCostumer(idCostumer: Long): List<Credit> {
        return creditRepository.findAllByCustomer(idCostumer)
    }

    override fun findByCreditCode(uuid: UUID,idCostumer: Long): Credit {
       val credit = creditRepository.findByCreditCode(uuid)
                ?:throw RuntimeException("Credit code $uuid not found")
        return if (credit.customer?.id == idCostumer)  credit
                  else throw RuntimeException("credit is not found for this User")
    }
}