package com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Betrokkene(
    @JsonProperty("uuid")
    override val uuid: String,
    @JsonProperty("url")
    override val url: String,
    @JsonProperty("wasPartij")
    val wasPartij: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidAndUrlReference? = null,
    @JsonProperty("hadKlantcontact")
    val hadKlantcontact: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidAndUrlReference,
    @JsonProperty("digitaleAdressen")
    val digitaleAdressen: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidAndUrlReference>,
    @JsonProperty("bezoekadres")
    val bezoekadres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Adres? = null,
    @JsonProperty("correspondentieadres")
    val correspondentieadres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Adres? = null,
    @JsonProperty("contactnaam")
    val contactnaam: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Contactnaam? = null,
    @JsonProperty("volledigeNaam")
    val volledigeNaam: String,
    @JsonProperty("rol")
    val rol: Rol,
    @JsonProperty("organisatienaam")
    val organisatienaam: String? = null,
    @JsonProperty("initiator")
    val initiator: Boolean,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Referable {
    enum class Rol {
        @JsonProperty("vertegenwoordiger")
        VERTEGENWOORDIGER,

        @JsonProperty("klant")
        KLANT,
    }
}