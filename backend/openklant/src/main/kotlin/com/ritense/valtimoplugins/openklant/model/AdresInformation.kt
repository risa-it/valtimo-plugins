package com.ritense.valtimoplugins.openklant.model

import com.ritense.valtimoplugins.openklant.dto.SoortDigitaalAdres

data class AdresInformation(
    val partijUuid: String,
    val adres: String,
    val soortDigitaalAdres: SoortDigitaalAdres,
    val referentie: String,
    val verificatieDatum: String,
) {
    companion object {
        fun fromActionProperties(
            partijUuid: String,
            adres: String,
            soortDigitaalAdres: String,
            referentie: String,
            verificatieDatum: String,
        ) = AdresInformation(
            partijUuid = partijUuid.trim(),
            adres = adres.trim(),
            soortDigitaalAdres = SoortDigitaalAdres.valueOf(soortDigitaalAdres.trim().uppercase()),
            referentie = referentie.trim(),
            verificatieDatum = verificatieDatum.trim(),
        )
    }
}
