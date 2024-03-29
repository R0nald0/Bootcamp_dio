package com.me.group.credit.sytem.webconfig

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun publicApi (): GroupedOpenApi{
        return GroupedOpenApi.builder()
                .group("springapplicatiocreditsystem-public")
                .pathsToMatch("/api/customers/**","/api/credit/**","/api/movimentation/**")
                .build()
    }
}