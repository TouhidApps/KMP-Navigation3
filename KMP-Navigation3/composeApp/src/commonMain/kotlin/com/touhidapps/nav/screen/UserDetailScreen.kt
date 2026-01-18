package com.touhidapps.nav.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.touhidapps.nav.nav.Route
import com.touhidapps.nav.nav.TwoPaneScene
import com.touhidapps.nav.nav.navAnimation
import com.touhidapps.nav.nav.rememberScreenEnv
import org.jetbrains.compose.ui.tooling.preview.Preview

fun EntryProviderScope<NavKey>.userDetailEntry() {
    entry<Route.UserDetail>(
        // To use multiple metadata use + sign
        metadata = TwoPaneScene.twoPane() + navAnimation
    ) { key ->

        UserDetailScreen(routeData = key.data) // (Landscape) After clicking a button from home

    }

}


@Preview(showBackground = true)
@Composable
fun UserDetailScreen(
    routeData: Route.UserDetail.Data? = null
) {

    val env = rememberScreenEnv()

    Column(modifier = Modifier.fillMaxSize().background(color = Color.Blue)) {
        Text("User Detail Screen (Screen 3)")
        Text(routeData?.userData?.userName ?: "")
        Text(routeData?.userData?.userPhone ?: "")
        Text("${routeData?.randomId ?: 0}")
        Button(onClick = {
            env.navigate(Route.Back)
        }) {
            Text(text = "Back")
        }
        Button(onClick = {
            env.navigate(Route.Home)
        }) {
            Text(text = "Home")
        }
    }

}