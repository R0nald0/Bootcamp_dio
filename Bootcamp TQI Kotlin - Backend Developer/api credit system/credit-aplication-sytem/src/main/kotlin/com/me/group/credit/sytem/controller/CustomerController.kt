package com.me.group.credit.sytem.controller

import com.me.group.credit.sytem.dto.*
import com.me.group.credit.sytem.service.ICustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController(
        private val serviceCustomer : ICustomerService
) {

    @PostMapping("/save")
    fun saveCustomer(@RequestBody @Valid costumerDTO :CostumerDTO):ResponseEntity<String>{
    val customer =  serviceCustomer.save(costumerDTO.toEnttity())
      return  ResponseEntity.ok("Customer ${customer.fistName} saved with success")
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
    fun upadteCosutumer(@RequestParam(value = "customerId") id:Long,
                        @RequestBody costumeUpdate: CostumerUpdateDT0):ResponseEntity<CustomerView>{
       val customer= serviceCustomer.findById(id).apply {
             costumeUpdate.toEntity(this)
         }
       val custemerUpadte = serviceCustomer.save(customer)
        val custumerView = CustomerView(custemerUpadte)
        return ResponseEntity.status(HttpStatus.CREATED).body(custumerView)

    }
}