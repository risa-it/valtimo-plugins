package com.ritense.valtimoplugins.samenwerkfunctionaliteit.autoconfigure

import com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.DefaultSamenwerkfunctionaliteitClient
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
@EnableConfigurationProperties
class SamenwerkfunctionaliteitConfiguration {
    @Bean
    fun samenwerkfunctionaliteitPluginClient(restClientBuilder: RestClient.Builder): DefaultSamenwerkfunctionaliteitClient =
        DefaultSamenwerkfunctionaliteitClient(
            restClientBuilder,
        )
}