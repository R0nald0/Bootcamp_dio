package com.me.group.credit.sytem.service

import com.me.group.credit.sytem.entity.Customer

interface ICostumerService {
    fun save(customer: Customer):Customer
    fun findById(idCostumer :Long):Customer
    fun delete (idCostumer : Long):Boolean
}