package com.ritense.valtimoplugins.samenwerkfunctionaliteit.service

import com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient
import org.springframework.stereotype.Service

@Service
class DefaultSamenwerkfunctionaliteitService(
    private val samenwerkfunctionaliteitClient: SamenwerkfunctionaliteitClient,
) : SamenwerkfunctionaliteitService