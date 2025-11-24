package com.touhidapps.nav.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.touhidapps.nav.nav.Route

@Composable
fun HomeScreen(route: (Route) -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "HOME - Navigate With Navigation3", modifier = Modifier.padding(10.dp))
        Button(onClick = {
            route(Route.User(name = "Touhid"))
        }) {
            Text("Basic Nav ->")
        }
        Button(onClick = {
            route(Route.List)
        }) {
            Text("List Detail (Landscape) ->")
        }
        Button(onClick = {
            route(Route.MyDialog)
        }) {
            Text("Alert Dialog ->")
        }

    }

}