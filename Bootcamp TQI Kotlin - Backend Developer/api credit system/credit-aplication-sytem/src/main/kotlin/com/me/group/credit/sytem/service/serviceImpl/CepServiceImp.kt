package com.me.group.credit.sytem.service.serviceImpl

import com.me.group.credit.sytem.dto.response.CepView
import com.me.group.credit.sytem.exeception.BusinessException
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class CepServiceImp {
    val restTemplate = RestTemplate();

    fun findAddress(cep :String):ResponseEntity<CepView>{
     val response =  restTemplate.getForEntity("https://viacep.com.br/ws/$cep/json/", CepView::class.java)
        if (response.statusCode.isError){
          throw BusinessException("cep nao encotrado")
        }
        return response
    }
 }