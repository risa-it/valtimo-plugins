package com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PartijIdentificator(
    @JsonProperty("uuid")
    override val uuid: String,
    @JsonProperty("url")
    override val url: String,
    @JsonProperty("identificeerdePartij")
    val identificeerdePartij: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference,
    @JsonProperty("partijIdentificator")
    val partijIdentificator: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Identificator,
    @JsonProperty("subIdentificatorVan")
    val subIdentificatorVan: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference?,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Referable