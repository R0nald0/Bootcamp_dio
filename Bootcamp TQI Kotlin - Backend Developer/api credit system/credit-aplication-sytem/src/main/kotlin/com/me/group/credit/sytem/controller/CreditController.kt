package com.me.group.credit.sytem.controller

import com.me.group.credit.sytem.dto.CreditDTO
import com.me.group.credit.sytem.dto.CreditView
import com.me.group.credit.sytem.dto.toEntity
import com.me.group.credit.sytem.entity.Credit
import com.me.group.credit.sytem.service.serviceImpl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("api/credit")
class CreditController(
  private val creditService: CreditService
) {

    @PostMapping
   fun saveCredit(@RequestBody creditDTO: CreditDTO):ResponseEntity<String>{
          val credit = creditService.save(creditDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Credit - ${credit.creditCode} saved")
   }

    @GetMapping
    fun findAllCreditByCustomer(
            @RequestParam(value = "customerId")
            idCustomer:Long) :ResponseEntity<List<CreditView>>{
         val listCredit = creditService.findAllByCostumer(idCustomer)
         val listCreditView =  listCredit.stream().map {credit->
              CreditView(credit)
          }.collect(Collectors.toList())
          return ResponseEntity.ok(listCreditView)
    }

    @GetMapping("/{idCustomer}")
    fun findByCreditCode(@RequestParam(value = "customerId") idCustomer: Long,
                         @PathVariable creditCode : UUID):ResponseEntity<CreditView>{
            val  credit= creditService.findByCreditCode( creditCode ,idCustomer)
            return ResponseEntity.ok(CreditView(credit))
    }

}