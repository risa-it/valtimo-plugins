package com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Onderwerpobject(
    @JsonProperty("uuid")
    override val uuid: String,
    @JsonProperty("url")
    override val url: String,
    @JsonProperty("klantcontact")
    val klantContact: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidAndUrlReference,
    @JsonProperty("wasKlantcontact")
    val wasKlantcontact: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidAndUrlReference? = null,
    @JsonProperty("onderwerpobjectidentificator")
    val onderwerpobjectidentificator: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Onderwerpobjectidentificator? = null,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Referable