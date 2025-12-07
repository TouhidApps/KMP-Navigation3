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
import com.touhidapps.nav.nav.LocalNavigator
import com.touhidapps.nav.nav.Route
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun HomeScreen() {

    val navigate = LocalNavigator.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "HOME - Navigate With Navigation3", modifier = Modifier.padding(10.dp))
        Button(onClick = {
            navigate(Route.User(name = "Touhid"))
        }) {
            Text("Basic Nav ->")
        }
        Button(onClick = {
            navigate(Route.List)
        }) {
            Text("List Detail (Landscape) ->")
        }
        Button(onClick = {
            navigate(Route.MyDialog)
        }) {
            Text("Alert Dialog ->")
        }

    }

}