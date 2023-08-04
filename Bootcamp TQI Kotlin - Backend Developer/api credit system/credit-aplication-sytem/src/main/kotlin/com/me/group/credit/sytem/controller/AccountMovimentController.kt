package com.me.group.credit.sytem.controller

import com.me.group.credit.sytem.dto.AccountMovimentDTO
import com.me.group.credit.sytem.dto.response.AccountMovimentView
import com.me.group.credit.sytem.dto.toAccountMoviment
import com.me.group.credit.sytem.entity.AccountMovement
import com.me.group.credit.sytem.service.IAccountMovimentService
import com.me.group.credit.sytem.service.ICustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/movimentation")
class AccountMovimentController(
    private val accountMovimentService: IAccountMovimentService,
    private val customerServiceImpl: ICustomerService
) {

    @PostMapping("/save")
    fun saveAccountMoviment(@RequestBody @Valid accountMoviment:AccountMovimentDTO):ResponseEntity<AccountMovimentView>{
        val toAccountMoviment = accountMoviment.toAccountMoviment()
        val savedAccountMoviment = accountMovimentService.saveAccountMoviment(toAccountMoviment)
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountMovimentView(savedAccountMoviment))
    }

    @GetMapping("/{idCustomer}")
    fun getAllMovimentsAccount(@PathVariable  idCustomer:Long):ResponseEntity<List<AccountMovimentView>>{
        val movimentList = accountMovimentService.getAllAccontMovimentCostumer(idCostumer = idCustomer)
           val listAccountView = movimentList.map {accountMoviment ->
                  AccountMovimentView(accountMoviment)
           }
         return ResponseEntity.status(HttpStatus.OK).body(listAccountView)
    }

    @GetMapping("/accountmoviments/{customer_id}")
    fun getAccountMovimentById(
        @PathVariable customer_id: Long,
        @RequestParam(value = "idmoviment")  idAccountMoviment :Long
    ):ResponseEntity<AccountMovimentView>{
        val accountMovement = accountMovimentService.findAccountMoviment(idAccountMoviment,customer_id)
           if (accountMovement !=null){
               return ResponseEntity.status(HttpStatus.OK).body(AccountMovimentView(accountMovement))
           }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
    }
}