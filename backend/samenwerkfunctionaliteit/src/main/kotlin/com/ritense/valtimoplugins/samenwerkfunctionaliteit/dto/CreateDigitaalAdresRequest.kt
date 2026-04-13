package com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateDigitaalAdresRequest(
    @JsonProperty("verstrektDoorBetrokkene")
    val verstrektDoorBetrokkene: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidReference? = null,
    @JsonProperty("verstrektDoorPartij")
    val verstrektDoorPartij: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidReference?,
    @JsonProperty("adres")
    val adres: String,
    @JsonProperty("soortDigitaalAdres")
    val soortDigitaalAdres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.SoortDigitaalAdres,
    @JsonProperty("isStandaardAdres")
    val isStandaardAdres: Boolean? = false,
    @JsonProperty("omschrijving")
    val omschrijving: String = "",
    @JsonProperty("referentie")
    val referentie: String?,
    @JsonProperty("verificatieDatum")
    val verificatieDatum: String? = null,
)