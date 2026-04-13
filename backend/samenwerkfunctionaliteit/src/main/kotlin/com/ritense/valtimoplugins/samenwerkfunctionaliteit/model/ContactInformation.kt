package com.ritense.valtimoplugins.samenwerkfunctionaliteit.model

data class ContactInformation(
    override val bsn: String,
    override val voorletters: String,
    override val voornaam: String,
    override val voorvoegselAchternaam: String,
    override val achternaam: String,
    val emailadres: String,
    val zaaknummer: String,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformation {
    companion object {
        fun fromActionProperties(
            bsn: String,
            voornaam: String,
            voorvoegselAchternaam: String,
            achternaam: String,
            emailadres: String,
            zaaknummer: String,
        ) = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.ContactInformation(
            bsn = bsn.trim(),
            voorletters = "",
            voornaam = voornaam.trim(),
            voorvoegselAchternaam = voorvoegselAchternaam.trim(),
            achternaam = achternaam.trim(),
            emailadres = emailadres.trim(),
            zaaknummer = zaaknummer.trim(),
        )
    }
}