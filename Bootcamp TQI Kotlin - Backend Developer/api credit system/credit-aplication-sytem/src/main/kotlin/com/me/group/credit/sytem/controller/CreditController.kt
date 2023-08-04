package com.me.group.credit.sytem.controller

import com.me.group.credit.sytem.dto.AccountMovimentDTO
import com.me.group.credit.sytem.dto.CreditDTO
import com.me.group.credit.sytem.dto.response.CreditView
import com.me.group.credit.sytem.dto.toAccountMoviment
import com.me.group.credit.sytem.enums.Status
import com.me.group.credit.sytem.enums.TitulosMovimentacao
import com.me.group.credit.sytem.extension.convertDateLongToString
import com.me.group.credit.sytem.service.IAccountMovimentService
import com.me.group.credit.sytem.service.ICreditService
import com.me.group.credit.sytem.service.ICustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("api/credit")
class CreditController(
    private val creditService: ICreditService,
    private val accountMovimentService: IAccountMovimentService,
    private val serviceCustomer :ICustomerService
) {

    @PostMapping("/save")
   fun saveCredit(@RequestBody  @Valid creditDTO: CreditDTO):ResponseEntity<CreditView>{
        val credit = creditService.save(creditDTO)
        serviceCustomer.upadateAccount(credit.creditValue,credit.customer!!, TitulosMovimentacao.PEDIDO_EMPRESTIMO)
        val accountMoviment  =AccountMovimentDTO(credit.customer?.id!!,Date().time,TitulosMovimentacao.PEDIDO_EMPRESTIMO,credit.creditValue)
        accountMovimentService.saveAccountMoviment(accountMoviment.toAccountMoviment())
        return ResponseEntity.status(HttpStatus.CREATED).body(CreditView(credit))
   }
    @GetMapping("/limitDate")
    fun getDateMinimun():ResponseEntity<String>{
        val dateMinimunLimit =  creditService.getDateLimit()
        val dateLongToString = Date().convertDateLongToString(dateMinimunLimit)
        return ResponseEntity.status(HttpStatus.OK).body(dateLongToString)
    }
    @GetMapping("/credits")
    fun findAllCreditByCustomer(
            @RequestParam(value = "customerId")
            idCustomer:Long) :ResponseEntity<List<CreditView>>{
         val listCredit = creditService.findAllByCostumer(idCustomer)
         val listCreditView =  listCredit.stream().map {credit->
              CreditView(credit)
          }.collect(Collectors.toList())
          return ResponseEntity.ok(listCreditView)
    }

    @GetMapping("credit/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") idCustomer: Long,
                         @PathVariable creditCode : UUID):ResponseEntity<CreditView>{
            val  credit= creditService.findByCreditCode( creditCode ,idCustomer)
            return ResponseEntity.ok(CreditView(credit))
    }
    @PatchMapping("/update/{creditId}")
    fun updateStateCredit(
        @PathVariable creditId: Long,
        @RequestBody  creditDTO: CreditDTO,
        @RequestParam(value = "stateCredit") creditState : Status,
    ):ResponseEntity<CreditView>{
        val resultCredit  = creditService.updateStateCredit(creditId,creditDTO.customerId,creditState)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(resultCredit!!))
        //TODO verificar nulidade
    }

}