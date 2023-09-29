package com.me.group.credit.sytem.websocketconfig

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

/*@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    @Override
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/api/connect").withSockJS()
        registry.addEndpoint("/api/connect")
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/chat")
        registry.setApplicationDestinationPrefixes("/app")
    }
}*/


@Configuration
@EnableWebSocketMessageBroker
class WebSocket : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.setApplicationDestinationPrefixes("/app")
        config.enableSimpleBroker("/canal","/queue")
        config.setUserDestinationPrefix("/queue")

    }

    override fun registerStompEndpoints(config: StompEndpointRegistry) {
        config.addEndpoint("conect").setAllowedOrigins("*")
        config.addEndpoint("conect").setAllowedOrigins("*").withSockJS()
    }
}

