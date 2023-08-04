package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.dto.CreditDTO
import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.enums.Status
import com.me.group.credit.sytem.exeception.BusinessException
import com.me.group.credit.sytem.extension.convertDateStringToLong
import com.me.group.credit.sytem.repository.CreditRepository
import com.me.group.credit.sytem.service.ICreditService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class CreditService(
        private val creditRepository : CreditRepository,
        private val costumerService : CustomerServiceImpl
) :ICreditService {
    override fun save(creditDTO: CreditDTO): Credit {
        val dateStringToLong = LocalDate.parse(creditDTO.dayOfInstallment, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val data = dateStringToLong
        val dataLimit = LocalDate.now().plusMonths(3L)

        if (data <= dataLimit) {
            val d = Date().convertDateStringToLong(creditDTO.dayOfInstallment)!!
            val credit = Credit(
                dayFirstInstallment = d,
                creditValue = creditDTO.creditValue,
                numberOfInstallments = creditDTO.numberOfInstallment,
                customer = costumerService.findById(creditDTO.customerId)
            )
            credit.apply {
                this.customer = costumerService.findById(credit.customer?.id!!)
            }
          val creditSaved =  creditRepository.save(credit)

           return creditSaved
        } else {
            throw BusinessException("the date must be on maximum three month forward")
        }
    }
        override fun getDateLimit(): Long {
            // val stringToLongDate = Date().convertDateStringToLong(dateLimit)

            val date = Calendar.getInstance()
            date.time
            date.add(Calendar.DAY_OF_MONTH, 20)
            //val dateLimit =LocalDate.now().plusDays(20)
            return date.time.time
        }

        override fun findAllByCostumer(idCustomer: Long): List<Credit> {
            try {
                return creditRepository.findAllByCustomer(idCustomer)
            } catch (businessException: BusinessException) {
                throw BusinessException("id $idCustomer customer not found")
            }
        }

        override fun findCreditCustomerById(idCredit: Long, idCustomer: Long): Credit? {
            try {
                val creditList = findAllByCostumer(idCustomer).filter { credit ->
                    if (credit.id == idCredit) {
                        return credit
                    } else {
                        return null
                    }
                }
                return creditList[0]
            } catch (businessException: BusinessException) {
                throw BusinessException("${idCredit} is null or credit not valid")
            }
        }

        override fun findByCreditCode(uuid: UUID, idCustomer: Long): Credit {
            val credit = creditRepository.findByCreditCode(uuid)
                ?: throw BusinessException("Credit code $uuid not found")

            return if (credit.customer?.id == idCustomer) credit
            else throw IllegalArgumentException("credit is not found for this User")
        }

        override fun updateStateCredit(idCredit: Long, idCustomer: Long, st: Status): Credit? {
            try {
                val credit = findCreditCustomerById(idCredit, idCustomer)
                val creditUpdate = credit?.copy(status = st)
                if (creditUpdate != null) {
                    return creditRepository.save(creditUpdate)
                }
                return null
            } catch (busissnesException: BusinessException) {
                throw BusinessException(busissnesException.message)
            }
        }



    }
