package com.ritense.valtimoplugins.samenwerkfunctionaliteit.model

import java.net.URI

data class KlantcontactOptions(
    override val klantinteractiesUrl: URI,
    override val token: String,
    val objectTypeId: String? = null,
    val objectUuid: String? = null,
    val bsn: String? = null,
    val partijUuid: String? = null,
) : com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.OpenKlantProperties(klantinteractiesUrl, token) {
    companion object {
        fun fromActionProperties(
            klantinteractiesUrl: URI,
            token: String,
            objectTypeId: String? = null,
            objectUuid: String? = null,
            bsn: String? = null,
            partijUuid: String? = null,
        ) = _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.model.KlantcontactOptions(
            klantinteractiesUrl,
            token = token.trim(),
            bsn = bsn?.trim(),
            partijUuid = partijUuid?.trim(),
            objectTypeId = objectTypeId?.trim(),
            objectUuid = objectUuid?.trim(),
        )
    }
}