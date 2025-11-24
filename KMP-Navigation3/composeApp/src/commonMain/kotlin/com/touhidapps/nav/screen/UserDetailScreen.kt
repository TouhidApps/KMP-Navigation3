package com.touhidapps.nav.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.touhidapps.nav.nav.Route


@Composable
fun UserDetailScreen(
    routeData: Route.UserDetail.Data? = null,
    route: (Route) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize().background(color = Color.Blue)) {
        Text("User Detail Screen (Screen 3)")
        Text(routeData?.userData?.userName ?: "")
        Text(routeData?.userData?.userPhone ?: "")
        Text("${routeData?.randomId ?: 0}")
        Button(onClick = {
            route(Route.Back)
        }) {
            Text(text = "Back")
        }
        Button(onClick = {
            route(Route.Home)
        }) {
            Text(text = "Home")
        }
    }

}