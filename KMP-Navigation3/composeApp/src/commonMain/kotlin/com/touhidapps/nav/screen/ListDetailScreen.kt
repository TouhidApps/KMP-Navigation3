package com.touhidapps.nav.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.touhidapps.nav.nav.Route
import com.touhidapps.nav.nav.rememberScreenEnv
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun EntryProviderScope<NavKey>.listDetailEntry() {
    entry<Route.ListDetail>(
        metadata = ListDetailSceneStrategy.detailPane()
    ) { key ->

        ListDetailScreen(key.flowerName)

    }

}

@Preview(showBackground = true)
@Composable
fun ListDetailScreen(
    flowerName: String? = null
) {

    val env = rememberScreenEnv()

    Column {
        Text(text = "List Detail")
        Text(text = "Flower Name: ${flowerName}")

        Button(onClick = {
            env.navigate(Route.Back)
        }) {
            Text("Back")
        }
    }

}