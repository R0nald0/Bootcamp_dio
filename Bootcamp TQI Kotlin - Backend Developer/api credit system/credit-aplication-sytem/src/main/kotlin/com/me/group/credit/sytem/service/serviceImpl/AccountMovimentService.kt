package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.dto.AccountMovimentDTO
import com.me.group.credit.sytem.dto.toAccountMoviment
import com.me.group.credit.sytem.entity.AccountMovement
import com.me.group.credit.sytem.exeception.BusinessException
import com.me.group.credit.sytem.repository.AccountMovimentRepository
import com.me.group.credit.sytem.service.IAccountMovimentService
import com.me.group.credit.sytem.service.ICustomerService
import org.springframework.stereotype.Service


@Service
class AccountMovimentService(
    private val accountMovimentRepository:AccountMovimentRepository,
    private val  customerServiceImpl: ICustomerService
) :IAccountMovimentService {
    override fun saveAccountMoviment(accountMovimentDTO: AccountMovimentDTO): AccountMovement {
        customerServiceImpl.findById(accountMovimentDTO.idCustomer)
        val accountMovement = accountMovimentDTO.toAccountMoviment()
        return accountMovimentRepository.save(accountMovement)

    }

    override fun getAllAccontMovimentCostumer(idCostumer: Long): List<AccountMovement> {
         try {
             val movements = accountMovimentRepository.findAllByCustomer(idCostumer)
             return movements
         }catch (  businessException : BusinessException){
             businessException.stackTrace
             throw BusinessException("fail to found all moviments")
         }
    }

    override fun findAccountMoviment(idAccountMovement: Long, idCustomer: Long): AccountMovement? {
        try {
            val movimentCostumer = getAllAccontMovimentCostumer(idCustomer)
          val accountMoviments = movimentCostumer.filter {accountMovement ->
                return  if (accountMovement.id == idAccountMovement) accountMovement
                else return null
            }
            return accountMoviments[0]
        }catch (  businessException : BusinessException){
            businessException.stackTrace
            throw BusinessException("fail to found account moviment")
        }
    }
/*    override fun enterMoney(valorEntrada: BigDecimal, idcustomer: Long, typrEntry : TitulosMovimentacao): AccountMovement {
        try {
            val customer = customerServiceImpl.findById(idcustomer)
            val accountMovement = AccountMovement(
                customer = customer,
                movimentValue = valorEntrada,
                dateMoviment = Date().time,
                type = typrEntry
            )
            val movimentSaved = accountMovimentRepository.save(accountMovement)

            return movimentSaved
        } catch (businessExption: BusinessException) {
            throw BusinessException("fail when entry value")
        }
    }*/

}