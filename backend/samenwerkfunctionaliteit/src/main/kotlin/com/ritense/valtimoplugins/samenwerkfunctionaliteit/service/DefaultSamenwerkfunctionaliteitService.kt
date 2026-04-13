package com.ritense.valtimoplugins.samenwerkfunctionaliteit.service

import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.CreateDigitaalAdresRequest
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Klantcontact
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.SoortDigitaalAdres
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidReference
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.AdresInformation
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.ContactInformation
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactCreationInformation
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformation

class DefaultSamenwerkfunctionaliteitService(
    private val openKlantClient: com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient,
    private val partijFactory: com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.PartijFactory,
    private val klantcontactFactory: com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.KlantcontactFactory,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.SamenwerkfunctionaliteitService {
    override fun storeContactInformation(
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
        contactInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.ContactInformation,
    ): String {
        val partij = openKlantClient.getPartijByBsn(contactInformation.bsn, properties)
        return if (partij != null) {
            if (!isPreferredAddress(contactInformation.emailadres, partij, properties)) {
                updateExistingPartij(partij, contactInformation, properties)
            }
            partij.uuid
        } else {
            createAndStoreNewPartij(contactInformation, properties)
        }
    }

    override fun getOrCreatePartij(
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
        partijInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformation,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij =
        openKlantClient.getPartijByBsn(partijInformation.bsn, properties)
            ?: createNewPartij(partijInformation, properties)

    override fun setDefaultDigitaalAdres(
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
        adresInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.AdresInformation,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres {
        clearDefaultForCurrentDigitaalAdressen(adresInformation, properties)

        val request =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.CreateDigitaalAdresRequest(
                verstrektDoorPartij =
                    _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidReference(
                        adresInformation.partijUuid,
                    ),
                adres = adresInformation.adres,
                soortDigitaalAdres = adresInformation.soortDigitaalAdres,
                isStandaardAdres = true,
                referentie = adresInformation.referentie,
                verificatieDatum = adresInformation.verificatieDatum,
            )

        return openKlantClient.createDigitaalAdres(
            request = request,
            properties = properties,
        )
    }

    override fun getAllKlantcontacten(
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions,
    ): List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Klantcontact> = openKlantClient.getKlantcontacten(properties).results

    override fun postKlantcontact(
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
        klantcontactCreationInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactCreationInformation,
    ) {
        val klantContactRequest = klantcontactFactory.createKlantcontactRequest(klantcontactCreationInformation)
        openKlantClient.postKlantcontact(
            request = klantContactRequest,
            properties = properties,
        )
    }

    private fun clearDefaultForCurrentDigitaalAdressen(
        adresInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.AdresInformation,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ) {
        openKlantClient
            .getDefaultAdressenBySoort(
                partijUuid = adresInformation.partijUuid,
                soortDigitaalAdres = adresInformation.soortDigitaalAdres,
                referentie = adresInformation.referentie,
                properties = properties,
            ).forEach {
                openKlantClient.patchDigitaalAdres(
                    digitaalAdresUuid = it.uuid,
                    patchData = mapOf("referentie" to ""),
                    properties = properties,
                )
            }
    }

    private fun isPreferredAddress(
        emailAddress: String,
        partij: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ): Boolean {
        val voorkeursAdresUuid = partij.voorkeursDigitaalAdres?.uuid ?: return false
        val voorkeursAdres = openKlantClient.getDigitaalAdresByUuid(voorkeursAdresUuid, properties)
        return voorkeursAdres.adres == emailAddress
    }

    private fun createDigitalAddress(
        partij: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij,
        contactInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.ContactInformation,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres =
        openKlantClient.createDigitaalAdres(
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.CreateDigitaalAdresRequest(
                verstrektDoorPartij = partij.getUuidReference(),
                adres = contactInformation.emailadres,
                soortDigitaalAdres = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.SoortDigitaalAdres.EMAIL,
                referentie = contactInformation.zaaknummer,
            ),
            properties,
        )

    private fun createNewPartij(
        partijInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformation,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij {
        val newPartij = partijFactory.createFromBsn(partijInformation)
        return openKlantClient.createPartij(newPartij, properties)
    }

    private fun updateExistingPartij(
        partij: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij,
        contactInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.ContactInformation,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ) {
        val digitaleAdressen =
            openKlantClient
                .getDigitaleAdressenByPartijByUuid(
                    partij.getObjectReference().uuid,
                    properties,
                ).toMutableList()

        val digitaleUniekeReferenties =
            digitaleAdressen.map {
                "${it.verstrektDoorPartij?.uuid},${it.referentie},${it.soortDigitaalAdres}"
            }

        // Maak alleen nieuwe aan wanneer deze uniek is (niet bestaat)
        if ("${partij.getUuidReference().uuid},${contactInformation.zaaknummer},${_root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.SoortDigitaalAdres.EMAIL}" !in
            digitaleUniekeReferenties
        ) {
            digitaleAdressen.add(createDigitalAddress(partij, contactInformation, properties))
        }

        updateDigitaleAdressenForPartij(partij, digitaleAdressen.toList(), properties)
    }

    private fun createAndStoreNewPartij(
        contactInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.ContactInformation,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ): String {
        val partij = openKlantClient.getPartijByBsn(contactInformation.bsn, properties)
        return if (partij != null) {
            if (!isPreferredAddress(contactInformation.emailadres, partij, properties)) {
                updateExistingPartij(partij, contactInformation, properties)
            }
            partij.uuid
        } else {
            val nieuwePartij = createNewPartij(contactInformation, properties)
            val nieuweDigitaleAdress = createDigitalAddress(nieuwePartij, contactInformation, properties)

            updateDigitaleAdressenForPartij(nieuwePartij, nieuweDigitaleAdress, properties)
            return nieuwePartij.uuid
        }
    }

    private fun updateDigitaleAdressenForPartij(
        partij: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij,
        digitaleAdressen: List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres>,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ) {
        val patchData =
            mapOf(
                "digitaleAdressen" to digitaleAdressen.map { it.getUuidReference() },
            )
        openKlantClient.patchPartij(partij.uuid, patchData, properties)
    }

    private fun updateDigitaleAdressenForPartij(
        partij: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij,
        digitaleAdress: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ) = updateDigitaleAdressenForPartij(partij, listOf(digitaleAdress), properties)
}