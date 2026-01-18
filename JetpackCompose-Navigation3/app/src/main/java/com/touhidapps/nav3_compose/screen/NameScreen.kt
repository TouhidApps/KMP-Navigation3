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
import com.touhidapps.nav3_compose.model.UserData
import com.touhidapps.nav3_compose.route.LocalNavigator
import com.touhidapps.nav3_compose.route.Route
import kotlin.random.Random

fun EntryProviderScope<NavKey>.nameEntry() {

    entry<Route.NameScreen>() { key -> // can pass data also if you use Home class

        NameScreen(routeDataName = key.name)

    }

}

@Composable
fun NameScreen(routeDataName: String? = null) {

    val navigate = LocalNavigator.current // use it inside CompositionLocalProvider

    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.Red)
    ) {
        Text(text = "Compose Name Screen. (Screen 2)")
        Text(text = "Name ${routeDataName}")
        Button(onClick = {

            val data = Route.DetailScreen.Data(
                randomId = Random.nextInt(1000, 5000),
                userData = UserData(
                    userName = "Touhidul Islam",
                    userPhone = "01786"
                )
            )

            navigate(Route.DetailScreen(data))

        }) {
            Text(text = "Go to Detail")
        }
    }
}

@Preview
@Composable
fun NameScreenPreview() {
    NameScreen()
}