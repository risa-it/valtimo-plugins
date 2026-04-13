package com.ritense.valtimoplugins.samenwerkfunctionaliteit.plugin

import com.ritense.plugin.annotation.Plugin
import com.ritense.plugin.annotation.PluginAction
import com.ritense.plugin.annotation.PluginActionProperty
import com.ritense.plugin.annotation.PluginProperty
import com.ritense.processlink.domain.ActivityTypeWithEventName
import com.ritense.valtimoplugins.samenwerkfunctionaliteit.service.SamenwerkfunctionaliteitService
import mu.KotlinLogging
import org.operaton.bpm.engine.delegate.DelegateExecution
import java.net.URI

@Plugin(
    key = "samenwerkfunctionaliteit",
    title = "DSO-Samenwerkfunctionaliteit Plugin",
    description = "DSO-Samenwerkfunctionaliteit Plugin",
)
@Suppress("UNUSED")
class SamenwerkfunctionaliteitPlugin(
    private val samenwerkfunctionaliteitService: SamenwerkfunctionaliteitService,
) {
    @PluginProperty(key = "baseUrl", secret = false, required = true)
    lateinit var baseUrl: URI

    @PluginProperty(key = "token", secret = true, required = true)
    lateinit var token: String

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}