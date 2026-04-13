package com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Categorie(
    @JsonProperty("uuid")
    override val uuid: String,
    @JsonProperty("url")
    override val url: String,
    @JsonProperty("naam")
    val naam: String,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Referable