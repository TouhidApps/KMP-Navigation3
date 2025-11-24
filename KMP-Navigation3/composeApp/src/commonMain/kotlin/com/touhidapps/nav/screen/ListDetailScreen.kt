package com.touhidapps.nav.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.touhidapps.nav.nav.Route

@Composable
fun ListDetailScreen(
    flowerName: String? = null,
    route: (Route) -> Unit
) {

    Column {
        Text(text = "List Detail")
        Text(text = "Flower Name: ${flowerName}")

        Button(onClick = {
            route(Route.Back)
        }) {
            Text("Back")
        }
    }

}