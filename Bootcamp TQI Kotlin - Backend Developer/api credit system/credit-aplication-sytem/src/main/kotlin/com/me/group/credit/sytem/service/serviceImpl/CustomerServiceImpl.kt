package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.exeception.BusinessException
import com.me.group.credit.sytem.repository.CustomerRepository
import com.me.group.credit.sytem.service.ICustomerService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CustomerServiceImpl(
        private val custumerRepository: CustomerRepository
):ICustomerService {

    override fun save(customer: Customer): Customer {
        return custumerRepository.save(customer)
    }

    override fun findById(idCustomer: Long): Customer {
    return    custumerRepository.findById(idCustomer).orElseThrow{
            throw BusinessException("id ${idCustomer} not found ")
        }
    }

    override fun delete(idCostumer: Long): Boolean{
            val customer = findById(idCostumer)
          if (customer !=null){
              custumerRepository.delete(customer)
              return true
          }
        return false

    }
}