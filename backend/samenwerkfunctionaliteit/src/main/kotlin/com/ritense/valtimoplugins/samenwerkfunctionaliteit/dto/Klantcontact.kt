package com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Klantcontact(
    @JsonProperty("uuid")
    override val uuid: String,
    @JsonProperty("url")
    override val url: String,
    @JsonProperty("gingOverOnderwerpobjecten")
    val gingOverOnderwerpobjecten: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference>,
    @JsonProperty("hadBetrokkenActoren")
    val hadBetrokkenActoren: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Actor>,
    @JsonProperty("omvatteBijlagen")
    val omvatteBijlagen: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference>,
    @JsonProperty("hadBetrokkenen")
    val hadBetrokkenen: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference>,
    @JsonProperty("leiddeTotInterneTaken")
    val leiddeTotInterneTaken: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference>,
    @JsonProperty("nummer")
    val nummer: String?,
    @JsonProperty("kanaal")
    val kanaal: String,
    @JsonProperty("onderwerp")
    val onderwerp: String,
    @JsonProperty("inhoud")
    val inhoud: String?,
    @JsonProperty("indicatieContactGelukt")
    val indicatieContactGelukt: Boolean?,
    @JsonProperty("taal")
    val taal: String,
    @JsonProperty("vertrouwelijk")
    val vertrouwelijk: Boolean,
    @JsonProperty("plaatsgevondenOp")
    val plaatsgevondenOp: String?,
    @JsonProperty("_expand")
    val expand: Any?,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Referable