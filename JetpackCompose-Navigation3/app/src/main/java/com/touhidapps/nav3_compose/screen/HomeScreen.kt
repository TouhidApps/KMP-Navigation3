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

fun EntryProviderScope<NavKey>.homeEntry() {

    //  entry<Route.AppHome> { Box() {} }

    entry<Route.Home>() { key -> // can pass data also if you use Home class

        HomeScreen(key.data)

    }

}

@Composable
fun HomeScreen(
    routeData: Route.Home.Data? = null
) {

    val navigate = LocalNavigator.current // use it inside CompositionLocalProvider

    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.Gray)
    ) {
        Text(text = "Compose Home Screen. (Screen 1)")
        Text(text = "Data from XML Activity: ${routeData?.randomId ?: 0}")
        Text(text = "Data from XML Activity: ${routeData?.userData?.userName ?: ""}")
        Text(text = "Data from XML Activity: ${routeData?.userData?.userPhone ?: ""}")
        Button(onClick = {
            navigate(Route.NameScreen(name = "Touhid"))
        }) {
            Text(text = "Go to Name")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}