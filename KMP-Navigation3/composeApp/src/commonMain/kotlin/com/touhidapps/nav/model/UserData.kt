package com.touhidapps.nav.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val userName: String? = null,
    val userPhone: String? = null,
)
