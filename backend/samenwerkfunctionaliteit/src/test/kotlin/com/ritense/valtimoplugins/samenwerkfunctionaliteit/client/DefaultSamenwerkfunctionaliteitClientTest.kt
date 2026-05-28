package com.ritense.valtimoplugins.samenwerkfunctionaliteit.client

import io.mockk.mockk
import org.springframework.web.client.RestClient

internal class DefaultSamenwerkfunctionaliteitClientTest {
    private val restClientBuilder = mockk<RestClient.Builder>(relaxed = true)
    private val client = DefaultSamenwerkfunctionaliteitClient(restClientBuilder)
}