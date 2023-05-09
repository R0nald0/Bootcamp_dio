package com.me.group.credit.sytem.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRepository :JpaRepository<CreditRepository,Long> {
}