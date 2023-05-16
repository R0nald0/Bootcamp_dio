package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.entity.Customer
import com.me.group.credit.sytem.repository.CustomerRepository
import com.me.group.credit.sytem.service.ICostumerService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CustomerServiceImpl(
        private val custumerRepository: CustomerRepository
):ICostumerService {

    override fun save(customer: Customer): Customer {
        return custumerRepository.save(customer)
    }

    override fun findById(idCostumer: Long): Customer {
    return    custumerRepository.findById(idCostumer).orElseThrow{
            throw RuntimeException("id ${idCostumer} not found ")
        }
    }

    override fun delete(idCostumer: Long): Boolean{
        try {
            custumerRepository.deleteById(idCostumer)
          return true
        }catch (e:Exception){
            e.stackTrace
            return false
        }
    }
}