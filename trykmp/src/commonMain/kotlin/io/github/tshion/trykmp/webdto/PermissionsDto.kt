package io.github.tshion.trykmp.webdto

import kotlinx.serialization.Serializable

/**
 * @param admin
 * @param maintain
 * @param push
 * @param triage
 * @param pull
 */
@Serializable
internal data class PermissionsDto(
    val admin: Boolean,
    val maintain: Boolean? = null,
    val push: Boolean,
    val triage: Boolean? = null,
    val pull: Boolean
)
