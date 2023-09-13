package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.enums.Status
import com.me.group.credit.sytem.enums.MovimentationType
import com.me.group.credit.sytem.exeception.AmountInvalidException
import com.me.group.credit.sytem.exeception.BusinessException
import com.me.group.credit.sytem.repository.CreditRepository
import com.me.group.credit.sytem.repository.CustomerRepository
import com.me.group.credit.sytem.service.ICustomerService
import jakarta.transaction.Transactional
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

    override fun getCustomerByAccountNumber(accountNumber: Long): Customer {
        try {
            val byAccount = custumerRepository.findByAccount(accountNumber)
           if (byAccount != null){
               if (byAccount.account.numberAccount == accountNumber){
                   return byAccount
               }
           }
            throw BusinessException("customer not found by account number: $accountNumber")
        }catch (businessException:BusinessException){
            throw BusinessException("fail to get account number $accountNumber")
        }
    }

    override fun findCustomerByEmail(email: String): Customer {
        val customer  = custumerRepository.findByEmail(email)
        if (customer !=null){
            return customer
        }
        throw BusinessException("customer not find by email: $email")
    }
    override fun findById(idCustomer: Long): Customer {

        return  custumerRepository.findById(idCustomer).orElseThrow{
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

    @Transactional
    override fun upadateAccount(valorEntrada: BigDecimal, customer: Customer, typrEntry : MovimentationType):Customer {
        try {
            if (typrEntry == MovimentationType.PEDIDO_EMPRESTIMO) {
                customer.account.accountBalanceBlocked += valorEntrada
            } else {
                customer.account.accountFreeBalance += valorEntrada
            }

            return custumerRepository.save(customer)

        } catch (businessExption: BusinessException) {
            throw BusinessException("fail when entry value")
        }
    }


    override fun upadateStateCredit(creditValue:BigDecimal, customer: Customer, status: Status): Customer {
        try {
            if (customer.account.accountBalanceBlocked <= BigDecimal(0) ) throw AmountInvalidException("")

            if (status == Status.APPROVED ) {
                 customer.account.accountBalanceBlocked -= creditValue
                 customer.account.accountFreeBalance    += creditValue
            } else if (status == Status.REJECT) {
                customer.account.accountBalanceBlocked -= creditValue
            }

            return custumerRepository.save(customer)

        } catch (businessException: BusinessException) {
            throw BusinessException("fail when entry value")
        } catch (amountInvalidExeption : AmountInvalidException){
            throw AmountInvalidException("Customer not have amount blocked")
        }


    }



}