package com.touhidapps.nav.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.touhidapps.nav.model.UserData
import com.touhidapps.nav.nav.LocalNavigator
import com.touhidapps.nav.nav.Route
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun UserScreen(
    routeDataName: String? = null
) {

    val navigate = LocalNavigator.current

    Column(modifier = Modifier.fillMaxSize().background(color = Color.Red)) {
        Text("User Screen (Screen 2)")
        Text("User Name: ${routeDataName}")
        Button(onClick = {
            navigate(Route.Back)
        }) {
            Text(text = "Back")
        }
        Button(onClick = {

            val r = Route.UserDetail.Data(
                userData = UserData(userName = "Touhid", userPhone = "01213"),
                randomId = Random.nextInt(1000, 5000)
            )
            navigate(Route.UserDetail(data = r))

        }) {
            Text(text = "Next")
        }
    }

}


