package com.ritense.valtimoplugins.samenwerkfunctionaliteit.service

import com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.DefaultSamenwerkfunctionaliteitClient
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DefaultSamenwerkfunctionaliteitServiceTest {
    @MockK
    lateinit var client: DefaultSamenwerkfunctionaliteitClient

    lateinit var service: SamenwerkfunctionaliteitService

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        service =
            DefaultSamenwerkfunctionaliteitService(
                client,
            )
    }
}