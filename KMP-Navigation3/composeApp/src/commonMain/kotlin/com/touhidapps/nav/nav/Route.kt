package com.touhidapps.nav.nav

import androidx.navigation3.runtime.NavKey
import com.touhidapps.nav.model.UserData
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class Route: NavKey {

    @Serializable
    data object Home: Route()

    @Serializable
    data class User(val name: String? = null): Route() // Single data transfer

    @Serializable
    data class UserDetail(val data: Data? = null) : Route() { // Multiple data transfer
        @Serializable
        data class Data(
            var userData: UserData? = null,
            var randomId: Int? = null
        )
    }

    @Serializable
    data object List: Route()

    @Serializable
    data class ListDetail(val flowerName: String): Route()

    @Serializable
    data object MyDialog: Route()

    @Serializable
    data object Back: Route()

    @Serializable
    data class BackWithData(@Contextual val any: Any? = null) : Route() // use Contextual if you use any

}