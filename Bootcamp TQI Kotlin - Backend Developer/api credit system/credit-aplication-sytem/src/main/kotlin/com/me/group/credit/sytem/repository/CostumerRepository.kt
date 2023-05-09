package com.me.group.credit.sytem.repository

import com.me.group.credit.sytem.entity.Costumer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CostumerRepository : JpaRepository<Costumer,Long>{
}