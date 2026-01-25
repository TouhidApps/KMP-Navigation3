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
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.touhidapps.nav.nav.Route
import com.touhidapps.nav.nav.TwoPaneScene
import com.touhidapps.nav.nav.rememberScreenEnv
import org.jetbrains.compose.ui.tooling.preview.Preview

fun EntryProviderScope<NavKey>.homeEntry() {
    entry<Route.Home>(
        metadata = TwoPaneScene.twoPane()
    ) { key ->

        HomeScreen() // (Landscape) After clicking a button from home

    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreen() {

    val env = rememberScreenEnv()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "HOME - Navigate With Navigation3", modifier = Modifier.padding(10.dp))
        Button(onClick = {
            env.navigate(Route.User(name = "Touhid"))
        }) {
            Text("Basic Nav ->")
        }
        Button(onClick = {
            env.navigate(Route.List)
        }) {
            Text("List Detail (+Landscape) ->")
        }
        Button(onClick = {
            env.navigate(Route.MyDialog)
        }) {
            Text("Alert Dialog ->")
        }

    }

}