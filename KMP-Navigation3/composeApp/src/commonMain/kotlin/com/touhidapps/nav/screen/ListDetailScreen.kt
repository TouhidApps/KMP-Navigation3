package com.touhidapps.nav.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.touhidapps.nav.nav.LocalNavigator
import com.touhidapps.nav.nav.Route
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ListDetailScreen(
    flowerName: String? = null
) {

    val navigate = LocalNavigator.current

    Column {
        Text(text = "List Detail")
        Text(text = "Flower Name: ${flowerName}")

        Button(onClick = {
            navigate(Route.Back)
        }) {
            Text("Back")
        }
    }

}