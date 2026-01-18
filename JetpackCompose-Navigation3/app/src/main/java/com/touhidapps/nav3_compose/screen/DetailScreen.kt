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
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.touhidapps.nav3_compose.route.LocalNavigator
import com.touhidapps.nav3_compose.route.Route

fun EntryProviderScope<NavKey>.detailEntry() {

    entry<Route.DetailScreen>() { key -> // can pass data also if you use Home class

        DetailScreen(routeData = key.data)

    }

}

@Composable
fun DetailScreen(
    routeData: Route.DetailScreen.Data? = null
) {

    val navigate = LocalNavigator.current // use it inside CompositionLocalProvider

    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.Green)
    ) {
        Text(text = "Compose Detail Screen. (Screen 3)")
        Text(text = "Name ${routeData?.userData?.userName ?: ""}")
        Text(text = "Phone ${routeData?.userData?.userPhone ?: ""}")
        Text(text = "Id ${routeData?.randomId ?: ""}")
        Button(onClick = {
            navigate(Route.Back)
        }) {
            Text(text = "Back")
        }
        Button(onClick = {
            navigate(Route.Home())
        }) {
            Text(text = "Compose Home")
        }
        Button(onClick = {
            navigate(Route.AppHome(msg = "XML Home from 3rd compose screen"))
        }) {
            Text(text = "App Home")
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen()
}