package com.me.group.credit.sytem.service

import com.me.group.credit.sytem.dto.CreditDTO
import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.enums.Status
import java.util.*

interface ICreditService {
    fun save (creditDTO: CreditDTO):Credit
    fun getDateLimit():Long
    fun findAllByCostumer(idCostumer :Long):List<Credit>
    fun findCreditCustomerById(idCredit: Long, idCustomer: Long): Credit?

    fun findByCreditCode(uuid: UUID,idCostumer: Long):Credit
    fun updateStateCredit(idCredit:Long,idCustomer: Long,st :Status):Credit?

}