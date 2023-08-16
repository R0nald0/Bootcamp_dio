package com.me.group.credit.sytem.controller

import com.me.group.credit.sytem.dto.*
import com.me.group.credit.sytem.dto.response.CustomerView
import com.me.group.credit.sytem.entity.AccountMovement
import com.me.group.credit.sytem.enums.TitulosMovimentacao
import com.me.group.credit.sytem.service.ICustomerService
import com.me.group.credit.sytem.service.serviceImpl.AccountMovimentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.util.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(
        private val serviceCustomer : ICustomerService,
        private val accountMovimentService:AccountMovimentService
) {

    @PostMapping("/save")
    fun saveCustomer(@RequestBody @Valid costumerDTO :CostumerDTO):ResponseEntity<CustomerView>{
    val customer =  serviceCustomer.save(costumerDTO.toEnttity())
      return  ResponseEntity.status(HttpStatus.CREATED).body(CustomerView(customer))
    }

    @GetMapping("/findaccountnumber")
  fun getcustomerByAccountNumber(
         @RequestParam(value = "accountNumber") accountNumber : Long
  ): ResponseEntity<CustomerView>{
        val customerByAccountNumber = serviceCustomer.getCustomerByAccountNumber(accountNumber)
        val customerView = CustomerView(customerByAccountNumber)
        return  ResponseEntity.status(HttpStatus.OK).body(customerView)
  }
    @GetMapping("/findemail")
    fun findCustomerByEmail(@RequestParam(value = "email") email:String):ResponseEntity<CustomerView>{
          val customer =serviceCustomer.findCustomerByEmail(email)
          val customerView = CustomerView(customer)
         return ResponseEntity.status(HttpStatus.OK).body(customerView)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long):ResponseEntity<CustomerView>{
        val customer = serviceCustomer.findById(id)
         val customerView = CustomerView(customer)
       return  ResponseEntity.ok(customerView)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long):ResponseEntity<Boolean>{
       val result = serviceCustomer.delete(id)
       return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId") id:Long,
                       @RequestBody costumeUpdate: CostumerUpdateDT0):ResponseEntity<CustomerView>{
       val customer= serviceCustomer.findById(id).apply {
             costumeUpdate.toEntity(this)
         }
        val custemerUpadte = serviceCustomer.save(customer)
        val custumerView = CustomerView(custemerUpadte)

        return ResponseEntity.status(HttpStatus.OK).body(custumerView)
    }

    @PatchMapping("/update/{idCustomer}")
    fun upadateAccount(
        @PathVariable idCustomer:Long,
        @RequestParam(value = "valor" ) valorEntrada: BigDecimal,
        @RequestParam(value = "type") type : TitulosMovimentacao
    ):ResponseEntity<CustomerView>{
        val customer  =serviceCustomer.findById(idCustomer)
        val upadateAccount = serviceCustomer.upadateAccount(valorEntrada,customer, type)
        val accountMovement = AccountMovimentDTO(
                idCustomer = upadateAccount.id!!,
                Date().time,
                type,
                valorEntrada
        )
        accountMovimentService.saveAccountMoviment(accountMovement)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(upadateAccount))
    }
}