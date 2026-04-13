package com.ritense.valtimoplugins.samenwerkfunctionaliteit.autoconfigure

import com.ritense.plugin.service.PluginService
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.client.SamenwerkfunctionaliteitClient
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.SamenwerkfunctionaliteitPluginFactory
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.DefaultSamenwerkfunctionaliteitService
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.SamenwerkfunctionaliteitService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
@EnableConfigurationProperties
class SamenwerkfunctionaliteitConfiguration {
    @Bean
    fun samenwerkfunctionaliteitPluginClient(openKlantRestClientBuilder: RestClient.Builder): SamenwerkfunctionaliteitClient =
        SamenwerkfunctionaliteitClient(
            openKlantRestClientBuilder,
        )

    @Bean
    fun openKlantService(samenwerkfunctionaliteitClient: SamenwerkfunctionaliteitClient): SamenwerkfunctionaliteitService =
        DefaultSamenwerkfunctionaliteitService(
            samenwerkfunctionaliteitClient,
        )

    @Bean
    fun openKlantPluginFactory(
        pluginService: PluginService,
        openKlantService: SamenwerkfunctionaliteitService,
    ): SamenwerkfunctionaliteitPluginFactory =
        SamenwerkfunctionaliteitPluginFactory(
            pluginService,
            openKlantService,
        )
}