package com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin

import com.ritense.plugin.PluginFactory
import com.ritense.plugin.service.PluginService
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.OpenKlantService
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.util.ReflectionUtil

class OpenKlantPluginFactory(
    pluginService: PluginService,
    private val openKlantPluginService: com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.OpenKlantService,
    private val reflectionUtil: com.ritense.valtimoplugins.samenwerkfunctionaliteit.util.ReflectionUtil,
) : PluginFactory<com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin>(pluginService) {
    override fun create() =
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin.OpenKlantPlugin(
            openKlantPluginService,
            reflectionUtil,
        )
}