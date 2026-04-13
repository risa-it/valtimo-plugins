package com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreatePartijRequest(
    @JsonProperty("nummer")
    val nummer: String,
    @JsonProperty("interneNotitie")
    val interneNotitie: String,
    @JsonProperty("digitaleAdressen")
    val digitaleAdressen: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidReference>?,
    @JsonProperty("voorkeursDigitaalAdres")
    val voorkeursDigitaalAdres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidReference?,
    @JsonProperty("rekeningnummers")
    val rekeningnummers: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidReference>?,
    @JsonProperty("voorkeursRekeningnummer")
    val voorkeursRekeningnummer: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidReference?,
    @JsonProperty("partijIdentificatoren")
    val partijIdentificatoren: List<Map<String, com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Identificator>>,
    @JsonProperty("soortPartij")
    val soortPartij: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij.SoortPartij,
    @JsonProperty("indicatieGeheimhouding")
    val indicatieGeheimhouding: Boolean?,
    @JsonProperty("voorkeurstaal")
    val voorkeurstaal: String,
    @JsonProperty("indicatieActief")
    val indicatieActief: Boolean,
    @JsonProperty("bezoekadres")
    val bezoekadres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Adres?,
    @JsonProperty("correspondentieAdres")
    val correspondentieAdres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Adres?,
    @JsonProperty("partijIdentificatie")
    val partijIdentificatie: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij.PartijIdentificatie?,
)