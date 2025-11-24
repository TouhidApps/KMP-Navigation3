package com.touhidapps.nav3_compose.route

import android.app.Activity
import android.content.Intent
import androidx.navigation3.runtime.NavKey
import com.touhidapps.nav3_compose.model.UserData
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
sealed class Route: NavKey {

    @Serializable
    data class AppHome(val msg: String? = null): Route() // Go to xml home UI

//    @Serializable
//    data object Home: Route() // No data transfer (Go to compose home UI)

    @Serializable
    data class Home(val data : Data? = null) : Route() { // With data transfer (Go to compose home UI)
        @Serializable
        data class Data(
            var randomId: Int? = null,
            val userData: UserData? = null
        )
    }

    @Serializable
    data class NameScreen(val name: String? = null): Route()

    @Serializable
    data class DetailScreen(val data : Data? = null) : Route() { // With data transfer
        @Serializable
        data class Data(
            var randomId: Int? = null,
            val userData: UserData? = null
        )
    }

    @Serializable
    data object Back: Route()

    @Serializable
    data object Incorrect: Route()

//    @Serializable
//    data class BackWithData(@Contextual val any: Any? = null) : Route() // use Contextual if you use any


}

/**
 * Use it to navigate between activity/fragment(xml) to compose activity
 * Can pass data via Route
 */
fun goToComposeActivity(r: Route, activity: Activity) {

    val intent = Intent(activity, ComposeCommonActivity::class.java).apply {
        putExtra(
            "route", Json.encodeToString(
                Route.serializer(),
                r
            )
        )
    }
    activity.startActivity(intent)

}