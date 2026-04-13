package com.ritense.valtimoplugins.samenwerkfunctionaliteit.service

import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Betrokkene.Rol
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Contactnaam
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.KlantcontactCreationRequest
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidReference
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactCreationInformation

class KlantcontactFactory {
    fun createKlantcontactRequest(
        klantContactCreationInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactCreationInformation,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.KlantcontactCreationRequest =
        if (klantContactCreationInformation.hasBetrokkene) {
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.KlantcontactCreationRequest(
                klantcontact = klantcontactRequest(klantContactCreationInformation),
                betrokkene = betrokkeneRequest(klantContactCreationInformation),
            )
        } else {
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.KlantcontactCreationRequest(
                klantcontact = klantcontactRequest(klantContactCreationInformation),
            )
        }

    private fun klantcontactRequest(
        klantContactCreationInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactCreationInformation,
    ) = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.KlantcontactCreationRequest.KlantcontactRequest(
        nummer = null,
        kanaal = klantContactCreationInformation.kanaal,
        onderwerp = klantContactCreationInformation.onderwerp,
        inhoud = klantContactCreationInformation.inhoud,
        indicateContactGelukt = true,
        taal = klantContactCreationInformation.taal,
        vertrouwelijk = klantContactCreationInformation.vertrouwelijk,
        plaatsgevondenOp = klantContactCreationInformation.plaatsgevondenOp,
    )

    private fun betrokkeneRequest(
        klantContactCreationInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactCreationInformation,
    ) = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.KlantcontactCreationRequest.BetrokkeneRequest(
        wasPartij =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidReference(
                uuid =
                    klantContactCreationInformation.partijUuid
                        ?: throw IllegalArgumentException("No partijUuid was specified to create a betrokkene request"),
            ),
        bezoekadres = null,
        correspondentieadres = null,
        contactnaam = contactNaam(klantContactCreationInformation),
        rol = Rol.KLANT,
        organisatienaam = null,
        initiator = true,
    )

    private fun contactNaam(
        klantContactCreationInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactCreationInformation,
    ) = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Contactnaam(
        voorletters = klantContactCreationInformation.voorletters,
        voornaam = klantContactCreationInformation.voornaam,
        voorvoegselAchternaam = klantContactCreationInformation.voorvoegselAchternaam,
        achternaam = klantContactCreationInformation.achternaam,
    )
}