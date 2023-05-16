package com.me.group.credit.sytem.service

import com.me.group.credit.sytem.entity.Credit
import java.util.UUID

interface ICreditService {
    fun save (credit: Credit):Credit
    fun findAllByCostumer(idCostumer :Long):List<Credit>

    fun findByCreditCode(uuid: UUID,idCostumer: Long):Credit
}