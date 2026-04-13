package com.ritense.valtimoplugins.samenwerkfunctionaliteit.client

import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.CreateDigitaalAdresRequest
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.CreatePartijRequest
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Klantcontact
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.KlantcontactCreationRequest
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.SoortDigitaalAdres
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties
import com.ritense.zgw.Page
import jakarta.validation.Valid
import mu.KotlinLogging
import org.jetbrains.annotations.VisibleForTesting
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.client.body
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.util.UriBuilder
import java.net.URI

class SamenwerkfunctionaliteitClient(
    private val openKlantRestClientBuilder: RestClient.Builder,
) {
    fun getPartijByBsn(
        bsn: String,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij? =
        try {
            restClient(properties)
                .get()
                .uri { uriBuilder ->
                    uriBuilder
                        .path(
                            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_PARTIJEN_PATH,
                        ).queryParam(
                            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_SOORT_PARTIJ_IDENTIFICATOR_PARAM,
                            "bsn",
                        ).queryParam(
                            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_PARTIJ_IDENTIFICATOR_PARAM,
                            bsn,
                        ).queryParam(
                            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_SOORT_PARTIJ_PARAM,
                            "persoon",
                        ).build()
                }.retrieve()
                .body<Page<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij>>()
                ?.results
                ?.firstOrNull()
        } catch (e: HttpServerErrorException.InternalServerError) {
            handleInternalServerError(e)
        } catch (e: RestClientResponseException) {
            handleResponseException(e, "Error fetching Partij")
        }

    fun createPartij(
        request: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.CreatePartijRequest,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij =
        try {
            restClient(properties)
                .post()
                .uri(
                    _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_PARTIJEN_PATH,
                ).body(request)
                .retrieve()
                .body<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij>()
                ?: throw IllegalStateException("Error creating Partij: response body was null")
        } catch (e: HttpServerErrorException.InternalServerError) {
            handleInternalServerError(e)
        } catch (e: RestClientResponseException) {
            handleResponseException(e, "Error creating Partij")
        }

    fun patchPartij(
        id: String,
        patchData: Map<String, Any>,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij =
        try {
            restClient(properties)
                .patch()
                .uri(
                    "${_root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_PARTIJEN_PATH}/$id",
                ).body(patchData)
                .retrieve()
                .body<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Partij>()
                ?: throw IllegalStateException("Error patching Partij: response body was null")
        } catch (e: HttpServerErrorException.InternalServerError) {
            handleInternalServerError(e)
        } catch (e: RestClientResponseException) {
            handleResponseException(e, "Error patching Partij")
        }

    fun getDigitaleAdressenByPartijByUuid(
        uuid: String,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ): List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres> =
        try {
            restClient(properties)
                .get()
                .uri { uriBuilder ->
                    uriBuilder
                        .path(
                            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_DIGITALE_ADRESSEN_PATH,
                        ).queryParam(
                            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_VERSTREKT_DOOR_PARTIJ_ID_PARAM,
                            uuid,
                        ).build()
                }.retrieve()
                .body<Page<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres>>()
                ?.results
                ?: throw IllegalStateException("Error fetching DigitaalAdres: response body was null")
        } catch (e: HttpServerErrorException.InternalServerError) {
            handleInternalServerError(e)
        } catch (e: RestClientResponseException) {
            handleResponseException(e, "Error fetching DigitaalAdres for partij: $uuid")
        }

    fun getDigitaalAdresByUuid(
        digitaalAdresUuid: String,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres =
        try {
            restClient(properties)
                .get()
                .uri(
                    "${_root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_DIGITALE_ADRESSEN_PATH}/$digitaalAdresUuid",
                ).retrieve()
                .body<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres>()
                ?: throw IllegalStateException("Error fetching DigitaalAdres: response body was null")
        } catch (e: HttpServerErrorException.InternalServerError) {
            handleInternalServerError(e)
        } catch (e: RestClientResponseException) {
            handleResponseException(e, "Error fetching DigitaalAdres with uuid: $digitaalAdresUuid")
        }

    fun getDefaultAdressenBySoort(
        partijUuid: String,
        soortDigitaalAdres: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.SoortDigitaalAdres,
        referentie: String,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ): List<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres> =
        try {
            restClient(properties)
                .get()
                .uri { uriBuilder ->
                    uriBuilder
                        .path(
                            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_DIGITALE_ADRESSEN_PATH,
                        ).queryParam(
                            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_VERSTREKT_DOOR_PARTIJ_ID_PARAM,
                            partijUuid,
                        ).queryParam(
                            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_SOORT_DIGITAAL_ADRES_PARAM,
                            soortDigitaalAdres.value,
                        ).queryParam(
                            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_REFERENTIE_PARAM,
                            referentie,
                        ).build()
                }.retrieve()
                .body<Page<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres>>()
                ?.results
                ?: throw IllegalStateException("Error fetching DigitaalAdres: response body was null")
        } catch (e: HttpServerErrorException.InternalServerError) {
            handleInternalServerError(e)
        } catch (e: RestClientResponseException) {
            handleResponseException(
                e,
                "Error fetching Default ${soortDigitaalAdres.value} Adressen for partij: $partijUuid",
            )
        }

    fun patchDigitaalAdres(
        digitaalAdresUuid: String,
        patchData: Map<String, Any>,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ) = try {
        restClient(properties)
            .patch()
            .uri(
                "${_root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_DIGITALE_ADRESSEN_PATH}/$digitaalAdresUuid",
            ).body(patchData)
            .retrieve()
            .body<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres>()
            ?: throw IllegalStateException("Error patching DigitaalAdres: response body was null")
    } catch (e: HttpServerErrorException.InternalServerError) {
        handleInternalServerError(e)
    } catch (e: RestClientResponseException) {
        handleResponseException(e, "Error patching DigitaalAdres with uuid: $digitaalAdresUuid")
    }

    fun createDigitaalAdres(
        request: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.CreateDigitaalAdresRequest,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres =
        try {
            restClient(properties)
                .post()
                .uri(
                    _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_DIGITALE_ADRESSEN_PATH,
                ).body(request)
                .retrieve()
                .body<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.DigitaalAdres>()
                ?: throw IllegalStateException("Error creating DigitaalAdres: response body was null")
        } catch (e: HttpServerErrorException.InternalServerError) {
            handleInternalServerError(e)
        } catch (e: RestClientResponseException) {
            handleResponseException(e, "Error creating DigitaalAdres")
        }

    fun getKlantcontacten(
        klantContactOptions: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions,
    ): Page<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Klantcontact> {
        if (klantContactOptions.bsn.isNullOrBlank() &&
            klantContactOptions.objectUuid.isNullOrBlank() &&
            klantContactOptions.partijUuid.isNullOrBlank()
        ) {
            return Page(count = 0, results = emptyList())
        }

        try {
            return restClient(klantContactOptions)
                .get()
                .uri { uriBuilder ->
                    buildOpenKlantUri(uriBuilder, klantContactOptions)
                }.retrieve()
                .body<Page<com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.Klantcontact>>()
                ?: throw IllegalStateException("Error fetching Klantcontacten: response body was null")
        } catch (e: HttpServerErrorException.InternalServerError) {
            handleInternalServerError(e)
        } catch (e: RestClientResponseException) {
            handleResponseException(e, "Error fetching Klantcontacten")
        }
    }

    fun postKlantcontact(
        @Valid @RequestBody request: com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.KlantcontactCreationRequest,
        properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
    ) {
        try {
            restClient(properties)
                .post()
                .uri(
                    _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_MAAK_KLANTCONTACT_PATH,
                ).body(request)
                .retrieve()
                .toBodilessEntity()
        } catch (e: HttpServerErrorException.InternalServerError) {
            handleInternalServerError(e)
        } catch (e: RestClientResponseException) {
            handleResponseException(e, "Error creating Klantcontact")
        }
    }

    private fun restClient(properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties): RestClient =
        openKlantRestClientBuilder
            .clone()
            .baseUrl(properties.klantinteractiesUrl.toASCIIString())
            .defaultHeader("Authorization", "Token ${properties.token}")
            .build()

    @VisibleForTesting
    internal fun buildOpenKlantUri(
        builder: UriBuilder,
        options: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions,
    ): URI {
        options.objectTypeId?.let {
            builder.queryParam(
                _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_OBJECTTYPE_PARAM,
                it,
            )
        }
        options.bsn?.let {
            builder.queryParam(
                _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_BSN_PARAM,
                it,
            )
        }
        options.objectUuid?.let {
            builder.queryParam(
                _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_OBJECT_ID_PARAM,
                it,
            )
        }
        options.partijUuid?.let {
            builder.queryParam(
                _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_PARTIJ_UUID_PARAM,
                it,
            )
        }
        return builder
            .path(
                _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.OK_KLANTCONTACTEN_PATH,
            ).build()
    }

    private fun handleInternalServerError(e: HttpServerErrorException.InternalServerError): Nothing {
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.logger.warn {
            "Response body:  ${e.responseBodyAsString}"
        }
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.logger.error(
            e,
        ) {
            "Internal Server Error calling Open Klant"
        }
        throw ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error calling OpenKlant",
            e,
        )
    }

    private fun handleResponseException(
        e: RestClientResponseException,
        reason: String,
    ): Nothing {
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.logger.warn(
            e,
        ) {
            "Client error calling Open Klant"
        }
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient.Companion.logger.warn {
            "Response body:  ${e.responseBodyAsString}"
        }
        throw ResponseStatusException(
            e.statusCode,
            reason,
            e,
        )
    }

    companion object {
        private const val OK_PARTIJEN_PATH = "partijen"
        private const val OK_KLANTCONTACTEN_PATH = "klantcontacten"
        private const val OK_DIGITALE_ADRESSEN_PATH = "digitaleadressen"
        private const val OK_MAAK_KLANTCONTACT_PATH = "maak-klantcontact"

        private const val OK_VERSTREKT_DOOR_PARTIJ_ID_PARAM = "verstrektDoorPartij__uuid"
        private const val OK_SOORT_PARTIJ_IDENTIFICATOR_PARAM = "partijIdentificator__codeSoortObjectId"
        private const val OK_PARTIJ_IDENTIFICATOR_PARAM = "partijIdentificator__objectId"
        private const val OK_SOORT_PARTIJ_PARAM = "soortPartij"
        private const val OK_SOORT_DIGITAAL_ADRES_PARAM = "soortDigitaalAdres"
        private const val OK_REFERENTIE_PARAM = "referentie"
        private const val OK_OBJECTTYPE_PARAM = "onderwerpobject__onderwerpobjectidentificatorCodeObjecttype"
        private const val OK_OBJECT_ID_PARAM = "onderwerpobject__onderwerpobjectidentificatorObjectId"
        private const val OK_BSN_PARAM = "hadBetrokkene__wasPartij__partijIdentificator__objectId"
        private const val OK_PARTIJ_UUID_PARAM = "hadBetrokkene__wasPartij__uuid"

        private val logger = KotlinLogging.logger { }
    }
}