package com.ritense.valtimoplugins.samenwerkfunctionaliteit.resolver

import com.ritense.processdocument.domain.impl.OperatonProcessInstanceId
import com.ritense.processdocument.service.ProcessDocumentService
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.OpenKlantService
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.util.ReflectionUtil
import com.ritense.valueresolver.ValueResolverFactory
import com.ritense.zakenapi.service.ZaakDocumentService
import org.operaton.bpm.engine.delegate.VariableScope
import java.util.UUID
import java.util.function.Function

class OpenKlantValueResolverFactory(
    private val processDocumentService: ProcessDocumentService,
    private val zaakDocumentService: ZaakDocumentService,
    private val openKlantService: com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.OpenKlantService,
    private val reflectionUtil: com.ritense.valtimoplugins.samenwerkfunctionaliteit.util.ReflectionUtil,
    private val properties: com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties,
) : ValueResolverFactory {
    override fun supportedPrefix(): String = "klant"

    override fun createResolver(documentId: String): Function<String, Any?> {
        val zaakUuid = zaakDocumentService.getZaakByDocumentIdOrThrow(UUID.fromString(documentId)).uuid

        return Function { requestedValue ->
            when (requestedValue) {
                "klantcontacten" -> getKlantcontacten(zaakUuid)
                "klantcontactenOrNull" -> getKlantcontactenOrNull(zaakUuid)
                else -> throw IllegalArgumentException("Unknown Open Klant column with name: $requestedValue")
            }
        }
    }

    override fun createResolver(
        processInstanceId: String,
        variableScope: VariableScope,
    ): Function<String, Any?> {
        val document = processDocumentService.getDocument(OperatonProcessInstanceId(processInstanceId), variableScope)
        return createResolver(document.id().toString())
    }

    override fun handleValues(
        processInstanceId: String,
        variableScope: VariableScope?,
        values: Map<String, Any?>,
    ) {
        TODO()
    }

    private fun getKlantcontacten(zaakUuid: UUID) = getKlantcontactenOrNull(zaakUuid) ?: emptyList<Any>()

    private fun getKlantcontactenOrNull(zaakUuid: UUID) =
        runCatching { openKlantService.getAllKlantcontacten(createKlantcontactOptions(zaakUuid)) }
            .getOrNull()
            ?.let { reflectionUtil.deepReflectedMapOf(it) }

    private fun createKlantcontactOptions(zaakUuid: UUID): com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions =
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions(
            klantinteractiesUrl = properties.klantinteractiesUrl,
            token = properties.token,
            objectTypeId = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.resolver.OpenKlantValueResolverFactory.Companion.OBJECT_TYPE_ID,
            objectUuid = zaakUuid.toString(),
        )

    companion object {
        private const val OBJECT_TYPE_ID = "zaak"
    }
}