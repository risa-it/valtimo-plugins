package com.ritense.valtimoplugins.samenwerkfunctionaliteit.model

data class PartijInformationImpl(
    override val bsn: String,
    override val voorletters: String,
    override val voornaam: String,
    override val voorvoegselAchternaam: String,
    override val achternaam: String,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformation {
    companion object {
        fun fromActionProperties(
            bsn: String,
            voorletters: String,
            voornaam: String,
            voorvoegselAchternaam: String,
            achternaam: String,
        ) = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformationImpl(
            bsn = bsn.trim(),
            voorletters = voorletters.trim(),
            voornaam = voornaam.trim(),
            voorvoegselAchternaam = voorvoegselAchternaam.trim(),
            achternaam = achternaam.trim(),
        )
    }
}