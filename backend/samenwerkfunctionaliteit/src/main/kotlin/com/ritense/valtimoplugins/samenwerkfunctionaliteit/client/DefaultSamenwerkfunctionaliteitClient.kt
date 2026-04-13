package com.ritense.valtimoplugins.samenwerkfunctionaliteit.client

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class DefaultSamenwerkfunctionaliteitClient(
    private val restClientBuilder: RestClient.Builder,
) : SamenwerkfunctionaliteitClient {
    companion object {
        private val logger = KotlinLogging.logger { }
    }
}