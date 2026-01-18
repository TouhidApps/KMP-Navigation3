package com.touhidapps.nav.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.DialogSceneStrategy
import com.touhidapps.nav.nav.Route
import com.touhidapps.nav.nav.rememberScreenEnv

fun EntryProviderScope<NavKey>.alertEntry() {
    entry<Route.MyDialog>(
        metadata = DialogSceneStrategy.dialog(
            DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
            )
        )
    ) { key ->

        AlertScreen()

    }

}

@Composable
fun AlertScreen() {

    val env = rememberScreenEnv()

    Column(
        modifier = Modifier.background(color = Color.White)
    ) {
        Text("This is an alert")
        Button(onClick = {
            env.navigate(Route.Back)
        }) {
            Text("Done")
        }
    }

}


