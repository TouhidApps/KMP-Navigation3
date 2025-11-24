package com.touhidapps.nav3_compose.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val userName: String? = null,
    val userPhone: String? = null
)