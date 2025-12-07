package com.touhidapps.nav.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.touhidapps.nav.nav.LocalNavigator
import com.touhidapps.nav.nav.Route
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun UserDetailScreen(
    routeData: Route.UserDetail.Data? = null
) {

    val navigate = LocalNavigator.current

    Column(modifier = Modifier.fillMaxSize().background(color = Color.Blue)) {
        Text("User Detail Screen (Screen 3)")
        Text(routeData?.userData?.userName ?: "")
        Text(routeData?.userData?.userPhone ?: "")
        Text("${routeData?.randomId ?: 0}")
        Button(onClick = {
            navigate(Route.Back)
        }) {
            Text(text = "Back")
        }
        Button(onClick = {
            navigate(Route.Home)
        }) {
            Text(text = "Home")
        }
    }

}