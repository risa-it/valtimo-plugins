package com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin

import com.ritense.plugin.PluginFactory
import com.ritense.plugin.service.PluginService

class SamenwerkfunctionaliteitPluginFactory(
    pluginService: PluginService,
    private val openKlantPluginService: com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.SamenwerkfunctionaliteitService,
) : PluginFactory<SamenwerkfunctionaliteitPlugin>(pluginService) {
    override fun create() =
        SamenwerkfunctionaliteitPlugin(
            openKlantPluginService,
        )
}