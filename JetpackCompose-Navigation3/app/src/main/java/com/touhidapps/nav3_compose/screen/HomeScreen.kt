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
fun HomeScreen(
    routeData: Route.Home.Data? = null,
    route: (Route) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.Gray)
    ) {
        Text(text = "Compose Home Screen. (Screen 1)")
        Text(text = "Data from XML Activity: ${routeData?.randomId ?: 0}")
        Text(text = "Data from XML Activity: ${routeData?.userData?.userName ?: ""}")
        Text(text = "Data from XML Activity: ${routeData?.userData?.userPhone ?: ""}")
        Button(onClick = {
            route(Route.NameScreen(name = "Touhid"))
        }) {
            Text(text = "Go to Name")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen() {

    }
}