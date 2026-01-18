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
import com.touhidapps.nav.model.UserData
import com.touhidapps.nav.nav.Route
import com.touhidapps.nav.nav.TwoPaneScene
import com.touhidapps.nav.nav.rememberScreenEnv
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

fun EntryProviderScope<NavKey>.userEntry() {
    entry<Route.User>(
        metadata = TwoPaneScene.twoPane()
    ) { key ->

        UserScreen(key.name) // (Landscape) After clicking a button from home

    }

}

@Preview(showBackground = true)
@Composable
fun UserScreen(
    routeDataName: String? = null
) {

    val env = rememberScreenEnv()

    Column(modifier = Modifier.fillMaxSize().background(color = Color.Red)) {
        Text("User Screen (Screen 2)")
        Text("User Name: ${routeDataName}")
        Button(onClick = {
            env.navigate(Route.Back)
        }) {
            Text(text = "Back")
        }
        Button(onClick = {

            val r = Route.UserDetail.Data(
                userData = UserData(userName = "Touhid", userPhone = "01213"),
                randomId = Random.nextInt(1000, 5000)
            )
            env.navigate(Route.UserDetail(data = r))

        }) {
            Text(text = "Next")
        }
    }

}


