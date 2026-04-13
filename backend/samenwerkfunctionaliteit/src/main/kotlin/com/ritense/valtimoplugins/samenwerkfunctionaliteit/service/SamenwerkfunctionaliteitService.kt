package com.ritense.valtimoplugins.samenwerkfunctionaliteit.service

interface SamenwerkfunctionaliteitService {
    fun storeContactInformation(
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
        contactInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.ContactInformation,
    ): String

    fun getOrCreatePartij(
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
        partijInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformation,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij

    fun setDefaultDigitaalAdres(
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
        adresInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.AdresInformation,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres

    fun getAllKlantcontacten(
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions,
    ): List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Klantcontact>

    fun postKlantcontact(
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
        klantcontactCreationInformation: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactCreationInformation,
    )
}