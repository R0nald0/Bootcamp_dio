package com.me.group.credit.sytem.websocketconfig

import com.me.group.credit.sytem.extension.convertDateLongToString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.converter.SimpleMessageConverter

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
@RequestMapping( path = ["/api/connect"])
class WebSocketController(
        private  val simpMessagin : SimpMessagingTemplate
) {
    @MessageMapping("/chatmessage")
    @SendTo("/canal")
    fun sendMessanger(@Payload mensgem :Messenger):Messenger{
         val formattr = Date().convertDateLongToString(Date().time)
          return mensgem.copy(horario = formattr!!)
    }

    @MessageMapping("/chatprivate")
    //@SendToUser("/queue/chatprivate")
    fun sendMensagerPrivate(@Payload mensagem :Messenger) :Messenger{

        simpMessagin.convertAndSendToUser(mensagem.remetente,"/chatprivate",mensagem)
        simpMessagin.convertAndSendToUser(mensagem.remetente,"/chatprivate",mensagem)
        return  mensagem
    }
}