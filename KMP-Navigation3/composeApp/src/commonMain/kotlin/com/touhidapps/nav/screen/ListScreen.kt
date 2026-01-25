package com.touhidapps.nav.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.touhidapps.nav.nav.Route
import com.touhidapps.nav.nav.rememberScreenEnv
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun EntryProviderScope<NavKey>.listEntry() {
    entry<Route.List>(
        metadata = ListDetailSceneStrategy.listPane(
            detailPlaceholder = {
                Text("Choose an item from the list")
            }
        )
    ) { key ->

        ListScreen()

    }

}

@Preview(showBackground = true)
@Composable
fun ListScreen() {

    val env = rememberScreenEnv()

    val flowerNames = listOf<String>(
        "Rose",
        "Tulip",
        "Lily",
        "Daisy",
        "Orchid",
        "Sunflower",
        "Peony",
        "Marigold",
        "Lavender",
        "Jasmine",
        "Hyacinth",
        "Iris",
        "Poppy",
        "Crocus",
        "Dahlia",
        "Zinnia",
        "Aster",
        "Begonia",
        "Foxglove",
        "Gladiolus"
    )

    Column {
        Text(text = "List of flowers")
        Button(onClick = {
            env.navigate(Route.Back)
        }) {
            Text("Back")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(flowerNames) { item ->
                Text(
                    item,
                    modifier = Modifier.clickable(enabled = true, onClick = {
                        env.navigate(Route.ListDetail(item))
                    }).padding(8.dp).fillMaxWidth()
                )
            }
        }

    }

}