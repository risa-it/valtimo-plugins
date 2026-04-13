package com.ritense.valtimoplugins.samenwerkfunctionaliteit.model

import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.SoortDigitaalAdres
import mu.KotlinLogging

data class AdresInformation(
    val partijUuid: String,
    val adres: String,
    val soortDigitaalAdres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.SoortDigitaalAdres,
    val referentie: String,
    val verificatieDatum: String,
) {
    companion object {
        private val logger = KotlinLogging.logger { }

        fun fromActionProperties(
            partijUuid: String,
            adres: String,
            soortDigitaalAdres: String,
            referentie: String,
            verificatieDatum: String,
        ) = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.AdresInformation(
            partijUuid = partijUuid.trim(),
            adres = adres.trim(),
            soortDigitaalAdres =
                _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.SoortDigitaalAdres.entries
                    .firstOrNull { it.name.equals(soortDigitaalAdres.trim(), ignoreCase = true) }
                    ?: _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.SoortDigitaalAdres.OVERIG
                        .also { logger.warn { "Unknown soortDigitaalAdres: {$soortDigitaalAdres}, using default: {$it}" } },
            referentie = referentie.trim(),
            verificatieDatum = verificatieDatum.trim(),
        )
    }
}