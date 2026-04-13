package com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin

import com.ritense.plugin.annotation.Plugin
import com.ritense.plugin.annotation.PluginAction
import com.ritense.plugin.annotation.PluginActionProperty
import com.ritense.plugin.annotation.PluginProperty
import com.ritense.processlink.domain.ActivityTypeWithEventName
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.AdresInformation
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.ContactInformation
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactCreationInformation
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformationImpl
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.OpenKlantService
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.util.ReflectionUtil
import mu.KotlinLogging
import org.operaton.bpm.engine.delegate.DelegateExecution
import java.net.URI

@Plugin(
    key = "openklant",
    title = "Open Klant 2 Plugin",
    description = "Open Klant 2 plugin",
)
@Suppress("UNUSED")
class OpenKlantPlugin(
    private val openKlantPluginService: com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.OpenKlantService,
    private val reflectionUtil: com.ritense.valtimoplugins.samenwerkfunctionaliteit.util.ReflectionUtil,
) {
    @PluginProperty(key = "klantinteractiesUrl", secret = false, required = true)
    lateinit var klantinteractiesUrl: URI

    @PluginProperty(key = "token", secret = true, required = true)
    lateinit var token: String

    @PluginAction(
        key = "store-contact-info",
        title = "Store Contactinfo",
        description = "Store contact info in Open Klant",
        activityTypes = [ActivityTypeWithEventName.SERVICE_TASK_START],
    )
    fun storeContactInformation(
        execution: DelegateExecution,
        @PluginActionProperty bsn: String,
        @PluginActionProperty firstName: String,
        @PluginActionProperty inFix: String,
        @PluginActionProperty lastName: String,
        @PluginActionProperty emailAddress: String,
        @PluginActionProperty caseUuid: String,
    ) {
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin.Companion.logger.info {
            "Store Contactinformation in Open Klant - ${execution.processBusinessKey}"
        }

        val contactInformation =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.ContactInformation.Companion.fromActionProperties(
                bsn = bsn,
                voornaam = firstName,
                voorvoegselAchternaam = inFix,
                achternaam = lastName,
                emailadres = emailAddress,
                zaaknummer = caseUuid,
            )
        val properties =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties(
                klantinteractiesUrl,
                token,
            )
        val partijUuid = openKlantPluginService.storeContactInformation(properties, contactInformation)

        execution.setVariable(
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin.Companion.OUTPUT_PARTIJ_UUID,
            partijUuid,
        )
    }

    @PluginAction(
        key = "get-or-create-partij",
        title = "Get or create Partij",
        description = "Create partij in Open Klant or gets the partij if already exists",
        activityTypes = [ActivityTypeWithEventName.SERVICE_TASK_START],
    )
    fun getOrCreatePartij(
        execution: DelegateExecution,
        @PluginActionProperty bsn: String,
        @PluginActionProperty voorletters: String,
        @PluginActionProperty voornaam: String,
        @PluginActionProperty voorvoegselAchternaam: String,
        @PluginActionProperty achternaam: String,
    ) {
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin.Companion.logger.info {
            "Get or Create partij in Open Klant - ${execution.processBusinessKey}"
        }

        val partijInformation =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.PartijInformationImpl.Companion
                .fromActionProperties(
                    bsn = bsn,
                    voorletters = voorletters,
                    voornaam = voornaam,
                    voorvoegselAchternaam = voorvoegselAchternaam,
                    achternaam = achternaam,
                )
        val properties =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties(
                klantinteractiesUrl,
                token,
            )
        val partijUuid =
            openKlantPluginService
                .getOrCreatePartij(properties, partijInformation)
                .uuid

        execution.setVariable(
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin.Companion.OUTPUT_PARTIJ_UUID,
            partijUuid,
        )
    }

    @PluginAction(
        key = "set-default-digitaal-adres",
        title = "Set default Digitaal Adres",
        description = "Sets a default digitaal adres in Open Klant",
        activityTypes = [ActivityTypeWithEventName.SERVICE_TASK_START],
    )
    fun setDefaultDigitaalAdres(
        execution: DelegateExecution,
        @PluginActionProperty resultPvName: String,
        @PluginActionProperty partijUuid: String,
        @PluginActionProperty adres: String,
        @PluginActionProperty soortDigitaalAdres: String,
        @PluginActionProperty verificatieDatum: String,
    ) {
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin.Companion.logger.info {
            "Sets a default Digitaal Adres in Open Klant - ${execution.processBusinessKey}"
        }

        val adresInformation =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.AdresInformation.Companion.fromActionProperties(
                partijUuid = partijUuid,
                adres = adres,
                soortDigitaalAdres = soortDigitaalAdres,
                referentie = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin.Companion.DEFAULT_DIGITALE_ADRES_REFERENCE,
                verificatieDatum = verificatieDatum,
            )
        val properties =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties(
                klantinteractiesUrl,
                token,
            )

        val digitaalAdres = openKlantPluginService.setDefaultDigitaalAdres(properties, adresInformation)

        execution.setVariable(resultPvName, digitaalAdres.uuid)
    }

    @PluginAction(
        key = "get-contact-moments-by-case-uuid",
        title = "Get contact history by case UUID",
        description = "Get contact history by case UUID from Open Klant",
        activityTypes = [ActivityTypeWithEventName.SERVICE_TASK_START],
    )
    fun getContactMoments(
        @PluginActionProperty caseUuid: String,
        @PluginActionProperty resultPvName: String,
        execution: DelegateExecution,
    ) {
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin.Companion.logger.info {
            "Fetching contact history from Open Klant by case UUID: $caseUuid - ${execution.processBusinessKey}"
        }

        val pluginProperties =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions.Companion.fromActionProperties(
                klantinteractiesUrl,
                token = token,
                objectUuid = caseUuid,
            )

        fetchKlantcontactenAndStore(
            execution = execution,
            resultPvName = resultPvName,
            pluginProperties = pluginProperties,
        )
    }

    @PluginAction(
        key = "get-contact-moments-by-bsn",
        title = "Get contact history by BSN",
        description = "Get contact history by BSN from Open Klant. Queries the API using the 'partij-identificator object-ID' parameter.",
        activityTypes = [ActivityTypeWithEventName.SERVICE_TASK_START],
    )
    fun getContactMomentsByBsn(
        @PluginActionProperty bsn: String,
        @PluginActionProperty resultPvName: String,
        execution: DelegateExecution,
    ) {
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin.Companion.logger.info {
            "Fetching contact history from Open Klant by BSN number — business key: ${execution.processBusinessKey}"
        }
        val pluginProperties =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions.Companion.fromActionProperties(
                klantinteractiesUrl,
                token = token,
                bsn = bsn,
            )

        fetchKlantcontactenAndStore(
            execution = execution,
            resultPvName = resultPvName,
            pluginProperties = pluginProperties,
        )
    }

    @PluginAction(
        key = "get-contact-moments-by-partij-uuid",
        title = "Get contact history by Partij UUID",
        description = "Get contact history by Partij UUID from Open Klant.",
        activityTypes = [ActivityTypeWithEventName.SERVICE_TASK_START],
    )
    fun getContactMomentsByPartijUuid(
        @PluginActionProperty partijUuid: String,
        @PluginActionProperty resultPvName: String,
        execution: DelegateExecution,
    ) {
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin.Companion.logger.info {
            "Fetching contact history from Open Klant by Partij UUID — business key: ${execution.processBusinessKey}"
        }
        val pluginProperties =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions.Companion.fromActionProperties(
                klantinteractiesUrl,
                token = token,
                partijUuid = partijUuid,
            )

        fetchKlantcontactenAndStore(
            execution = execution,
            resultPvName = resultPvName,
            pluginProperties = pluginProperties,
        )
    }

    @PluginAction(
        key = "register-klantcontact",
        title = "Register klantcontact",
        description = "Registers a new klantcontact to OpenKlant",
        activityTypes = [ActivityTypeWithEventName.SERVICE_TASK_START],
    )
    fun postKlantContact(
        @PluginActionProperty kanaal: String,
        @PluginActionProperty onderwerp: String,
        @PluginActionProperty inhoud: String,
        @PluginActionProperty vertrouwelijk: String,
        @PluginActionProperty taal: String,
        @PluginActionProperty plaatsgevondenOp: String,
        @PluginActionProperty hasBetrokkene: Boolean,
        @PluginActionProperty partijUuid: String?,
        @PluginActionProperty voorletters: String?,
        @PluginActionProperty voornaam: String?,
        @PluginActionProperty voorvoegselAchternaam: String?,
        @PluginActionProperty achternaam: String?,
        execution: DelegateExecution,
    ) {
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin.Companion.logger.info {
            "Registering klantcontact - ${execution.processBusinessKey}"
        }

        val klantcontactCreationInformation =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactCreationInformation.Companion
                .fromActionProperties(
                    kanaal = kanaal,
                    onderwerp = onderwerp,
                    inhoud = inhoud,
                    vertrouwelijk = vertrouwelijk,
                    taal = taal,
                    plaatsgevondenOp = plaatsgevondenOp,
                    hasBetrokkene = hasBetrokkene,
                    partijUuid = partijUuid,
                    voorletters = voorletters,
                    voornaam = voornaam,
                    voorvoegselAchternaam = voorvoegselAchternaam,
                    achternaam = achternaam,
                )
        val properties =
            _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties(
                klantinteractiesUrl,
                token,
            )

        openKlantPluginService.postKlantcontact(
            properties,
            klantcontactCreationInformation,
        )
    }

    private fun fetchKlantcontactenAndStore(
        execution: DelegateExecution,
        resultPvName: String,
        pluginProperties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions,
    ) {
        val klantcontacten = openKlantPluginService.getAllKlantcontacten(pluginProperties)
        val contactenMaps = klantcontacten.map { reflectionUtil.deepReflectedMapOf(it) }
        execution.setVariable(resultPvName, contactenMaps)
    }

    companion object {
        private const val OUTPUT_PARTIJ_UUID = "partijUuid"
        private const val DEFAULT_DIGITALE_ADRES_REFERENCE = "portaalvoorkeur"
        private val logger = KotlinLogging.logger { }
    }
}