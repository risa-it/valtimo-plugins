package com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DigitaalAdres(
    @JsonProperty("uuid")
    override val uuid: String,
    @JsonProperty("url")
    override val url: String,
    @JsonProperty("verstrektDoorBetrokkene")
    val verstrektDoorBetrokkene: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference?,
    @JsonProperty("verstrektDoorPartij")
    val verstrektDoorPartij: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference?,
    @JsonProperty("adres")
    val adres: String,
    @JsonProperty("soortDigitaalAdres")
    val soortDigitaalAdres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.SoortDigitaalAdres,
    @JsonProperty("isStandaardAdres")
    val isStandaardAdres: Boolean?,
    @JsonProperty("omschrijving")
    val omschrijving: String?,
    @JsonProperty("referentie")
    val referentie: String?,
    @JsonProperty("_expand")
    val expand: Any?,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Referable