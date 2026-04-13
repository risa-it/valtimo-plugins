package com.ritense.valtimoplugins.samenwerkfunctionaliteit.service

import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Contactnaam
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.CreatePartijRequest
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Identificator
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformation

class PartijFactory {
    fun createFromBsn(
        partijInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformation,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.CreatePartijRequest =
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.CreatePartijRequest(
            nummer = "",
            interneNotitie = "",
            digitaleAdressen = emptyList(),
            voorkeursDigitaalAdres = null,
            rekeningnummers = emptyList(),
            voorkeursRekeningnummer = null,
            partijIdentificatoren = listOf(getPartijIdentificator(partijInformation)),
            soortPartij = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij.SoortPartij.PERSOON,
            indicatieGeheimhouding = false,
            voorkeurstaal = "nld",
            indicatieActief = true,
            bezoekadres = null,
            correspondentieAdres = null,
            partijIdentificatie = getPartijIdentificatie(partijInformation),
        )

    private fun getPartijIdentificator(
        partijInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformation,
    ): Map<String, com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Identificator> {
        val identificator =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Identificator(
                objectId = partijInformation.bsn,
                codeObjecttype = "natuurlijk_persoon",
                codeRegister = "brp",
                codeSoortObjectId = "bsn",
            )
        return mapOf("partijIdentificator" to identificator)
    }

    private fun getPartijIdentificatie(
        partijInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformation,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij.PartijIdentificatie {
        val contactnaam =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Contactnaam(
                voorletters = partijInformation.voorletters,
                voornaam = partijInformation.voornaam,
                voorvoegselAchternaam = partijInformation.voorvoegselAchternaam,
                achternaam = partijInformation.achternaam,
            )
        return _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij
            .PartijIdentificatie(contactnaam)
    }
}