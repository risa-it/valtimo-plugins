package com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

data class Partij(
    @JsonProperty("uuid")
    override val uuid: String,
    @JsonProperty("url")
    override val url: String,
    @JsonProperty("nummer")
    val nummer: String?,
    @JsonProperty("interneNotitie")
    val interneNotitie: String?,
    @JsonProperty("betrokkenen")
    val betrokkenen: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference>,
    @JsonProperty("categorieRelaties")
    val categorieRelaties: List<CategorieRelatiePartij>,
    @JsonProperty("digitaleAdressen")
    val digitaleAdressen: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference>?,
    @JsonProperty("voorkeursDigitaalAdres")
    val voorkeursDigitaalAdres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference?,
    @JsonProperty("vertegenwoordigden")
    val vertegenwoordigden: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference>,
    @JsonProperty("rekeningnummers")
    val rekeningnummers: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference>?,
    @JsonProperty("voorkeursRekeningnummer")
    val voorkeursRekeningnummer: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference?,
    @JsonProperty("partijIdentificatoren")
    val partijIdentificatoren: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.PartijIdentificator>?,
    @JsonProperty("soortPartij")
    val soortPartij: SoortPartij,
    @JsonProperty("indicatieGeheimhouding")
    val indicatieGeheimhouding: Boolean?,
    @JsonProperty("voorkeurstaal")
    val voorkeurstaal: String?,
    @JsonProperty("indicatieActief")
    val indicatieActief: Boolean,
    @JsonProperty("bezoekadres")
    val bezoekadres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Adres?,
    @JsonProperty("correspondentieadres")
    val correspondentieAdres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Adres?,
    @JsonProperty("partijIdentificatie")
    val partijIdentificatie: PartijIdentificatie?,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Referable {
    data class CategorieRelatiePartij(
        @JsonProperty("uuid")
        val uuid: String,
        @JsonProperty("url")
        val url: String,
        @JsonProperty("categorieNaam")
        val categorieNaam: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Categorie?,
        @JsonProperty("beginDatum")
        val beginDatum: String?,
        @JsonProperty("eindDatum")
        val eindDatum: String?,
    )

    enum class SoortPartij {
        @JsonProperty("contactpersoon")
        CONTACTPERSOON,

        @JsonProperty("persoon")
        PERSOON,

        @JsonProperty("organisatie")
        ORGANISATIE,

        @JsonProperty("ExpandPartij")
        EXPAND_PARTIJ,
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    data class PartijIdentificatie(
        @JsonProperty("contactnaam")
        val contactnaam: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Contactnaam?,
        @JsonProperty("volledigeNaam")
        val volledigeNaam: String? = null,
    )
}