package com.touhidapps.nav.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.touhidapps.nav.model.UserData
import com.touhidapps.nav.nav.Route
import kotlin.random.Random


@Composable
fun UserScreen(
    routeDataName: String? = null,
    route: (Route) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize().background(color = Color.Red)) {
        Text("User Screen (Screen 2)")
        Text("User Name: ${routeDataName}")
        Button(onClick = {
            route(Route.Back)
        }) {
            Text(text = "Back")
        }
        Button(onClick = {

            val r = Route.UserDetail.Data(
                userData = UserData(userName = "Touhid", userPhone = "01213"),
                randomId = Random.nextInt(1000, 5000)
            )
            route(Route.UserDetail(data = r))

        }) {
            Text(text = "Next")
        }
    }

}


