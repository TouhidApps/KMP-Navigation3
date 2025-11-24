package com.touhidapps.nav3_compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.touhidapps.nav3_compose.route.Route

@Composable
fun DetailScreen(
    routeData: Route.DetailScreen.Data? = null,
    route: (Route) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.Green)
    ) {
        Text(text = "Compose Detail Screen. (Screen 3)")
        Text(text = "Name ${routeData?.userData?.userName ?: ""}")
        Text(text = "Phone ${routeData?.userData?.userPhone ?: ""}")
        Text(text = "Id ${routeData?.randomId ?: ""}")
        Button(onClick = {
            route(Route.Back)
        }) {
            Text(text = "Back")
        }
        Button(onClick = {
            route(Route.Home())
        }) {
            Text(text = "Compose Home")
        }
        Button(onClick = {
            route(Route.AppHome(msg = "XML Home from 3rd compose screen"))
        }) {
            Text(text = "App Home")
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen() {

    }
}