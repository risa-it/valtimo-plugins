package com.ritense.valtimoplugins.samenwerkfunctionaliteit.autoconfigure

import com.ritense.plugin.service.PluginService
import com.ritense.processdocument.service.ProcessDocumentService
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.OpenKlantClient
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPluginFactory
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.resolver.OpenKlantValueResolverFactory
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.DefaultOpenKlantService
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.KlantcontactFactory
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.OpenKlantService
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.PartijFactory
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.util.ReflectionUtil
import com.ritense.zakenapi.service.ZaakDocumentService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import java.net.URI

@Configuration
@EnableConfigurationProperties
class OpenKlantAutoConfiguration {
    @Bean
    fun openKlantPluginClient(
        openKlantRestClientBuilder: RestClient.Builder,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.OpenKlantClient =
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.OpenKlantClient(
            openKlantRestClientBuilder,
        )

    @Bean
    fun partijFactory(): com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.PartijFactory =
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.service
            .PartijFactory()

    @Bean
    fun klantContactFactory(): com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.KlantcontactFactory =
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.service
            .KlantcontactFactory()

    @Bean
    fun openKlantService(
        openKlantClient: com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.OpenKlantClient,
        partijFactory: com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.PartijFactory,
        klantcontactFactory: com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.KlantcontactFactory,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.OpenKlantService =
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.DefaultOpenKlantService(
            openKlantClient,
            partijFactory,
            klantcontactFactory,
        )

    @Bean
    fun openKlantPluginFactory(
        pluginService: PluginService,
        openKlantService: com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.OpenKlantService,
        reflectionUtil: com.ritense.valtimoplugins.samenwerkfunctionaliteit.util.ReflectionUtil,
    ): com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPluginFactory =
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPluginFactory(
            pluginService,
            openKlantService,
            reflectionUtil,
        )

    @Bean
    fun openKlantValueResolverFactory(
        processDocumentService: ProcessDocumentService,
        zaakDocumentService: ZaakDocumentService,
        openKlantService: com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.OpenKlantService,
        reflectionUtil: com.ritense.valtimoplugins.samenwerkfunctionaliteit.util.ReflectionUtil,
        @Value("\${AUTODEPLOYMENT_PLUGINCONFIG_OPENKLANT_KLANTINTERACTIES_URL}")
        klantinteractieUrl: String,
        @Value("\${AUTODEPLOYMENT_PLUGINCONFIG_OPENKLANT_AUTHORIZATION_TOKEN}")
        openKlantToken: String,
    ) = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.resolver.OpenKlantValueResolverFactory(
        processDocumentService,
        zaakDocumentService,
        openKlantService,
        reflectionUtil,
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties(
            URI.create(
                klantinteractieUrl,
            ),
            openKlantToken,
        ),
    )

    @Bean
    fun reflectionUtil() =
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.util
            .ReflectionUtil()
}