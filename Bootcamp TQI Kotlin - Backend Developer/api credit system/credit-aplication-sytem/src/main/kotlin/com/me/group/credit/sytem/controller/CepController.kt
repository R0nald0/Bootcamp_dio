package com.me.group.credit.sytem.controller

import com.me.group.credit.sytem.dto.response.CepView
import com.me.group.credit.sytem.entity.Address
import com.me.group.credit.sytem.service.serviceImpl.CepServiceImp
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate


@RequestMapping("api/cep")
@RestController
class CepController (
        private val addressService : CepServiceImp
){

    @GetMapping()
    fun getCep(@RequestParam(value = "cep") cep :String): ResponseEntity<CepView> {
        return  addressService.findAddress(cep);
    }
}