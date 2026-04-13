package com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto

interface Referable {
    val uuid: String
    val url: String

    fun getObjectReference(): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.ObjectReference =
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto
            .ObjectReference(uuid, url)

    fun getUuidReference(): com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto.UuidReference =
        _root_ide_package_.com.ritense.valtimoplugins.samenwerkfunctionaliteit.dto
            .UuidReference(uuid)
}