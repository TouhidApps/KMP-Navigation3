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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.touhidapps.nav.nav.Route

@Composable
fun ListScreen(route: (Route) -> Unit) {

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
        Text(text = "List")
        Button(onClick = {
            route(Route.Back)
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
                        route(Route.ListDetail(item))
                    }).padding(8.dp).fillMaxWidth()
                )
            }
        }

        Button(onClick = {
            route(Route.Back)
        }) {
            Text("Back")
        }
    }

}