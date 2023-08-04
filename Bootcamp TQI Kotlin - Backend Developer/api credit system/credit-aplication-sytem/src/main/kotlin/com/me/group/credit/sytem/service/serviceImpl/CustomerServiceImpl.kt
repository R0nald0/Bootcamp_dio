package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.Status
import com.me.group.credit.sytem.enums.TitulosMovimentacao
import com.me.group.credit.sytem.exeception.BusinessException
import com.me.group.credit.sytem.repository.CreditRepository
import com.me.group.credit.sytem.repository.CustomerRepository
import com.me.group.credit.sytem.service.ICustomerService
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CustomerServiceImpl(
        private val custumerRepository: CustomerRepository,
        private val creditRepository: CreditRepository,
):ICustomerService {
    override fun save(customer: Customer): Customer {
        return custumerRepository.save(customer)
    }

    override fun findCustomerByEmail(email: String): Customer {
        val customer  = custumerRepository.findByEmail(email)
        if (customer !=null){
            return customer
        }
        throw BusinessException("customer not find by email: $email")
    }
    override fun findById(idCustomer: Long): Customer {
    return    custumerRepository.findById(idCustomer).orElseThrow{
            throw BusinessException("id ${idCustomer} not found")
        }
    }

    override fun delete(idCostumer: Long): Boolean{
            val customer = findById(idCostumer)
             val lisCredits =  creditRepository.findAllByCustomer(idCostumer).filter {
                    it.status == Status.APPROVED
             }

          if (customer !=null && lisCredits.isEmpty()){
              custumerRepository.delete(customer)
              return true
          }
        throw BusinessException("Customer ${customer.fistName} have pending Crédit")
        return false

    }

    override fun upadateAccount(valorEntrada: BigDecimal, customer: Customer, typrEntry : TitulosMovimentacao):Customer {
        try {
            if (typrEntry == TitulosMovimentacao.PEDIDO_EMPRESTIMO) {
                customer.account.accountBalanceBlocked += valorEntrada
            }else{
                customer.account.accountFreeBalance += valorEntrada
            }
            val savedCustomer = custumerRepository.save(customer)

            return savedCustomer

        } catch (businessExption: BusinessException) {
            throw BusinessException("fail when entry value")
        }
    }



}