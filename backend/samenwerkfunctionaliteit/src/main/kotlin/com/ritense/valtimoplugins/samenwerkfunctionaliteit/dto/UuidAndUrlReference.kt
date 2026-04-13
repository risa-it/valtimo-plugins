package com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UuidAndUrlReference(
    @JsonProperty("uuid")
    override val uuid: String,
    @JsonProperty("url")
    override val url: String,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Referable