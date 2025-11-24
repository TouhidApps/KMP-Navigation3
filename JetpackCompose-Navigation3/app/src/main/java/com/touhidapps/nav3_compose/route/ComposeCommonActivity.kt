package com.touhidapps.nav3_compose.route

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.serialization.json.Json


class ComposeCommonActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val routeJson = intent.getStringExtra("route")
        val initialRoute = if (routeJson != null) {
            Json.decodeFromString(Route.serializer(), routeJson)
        } else {
            Route.Incorrect
        }

        setContent {
            MainNavigation(route = initialRoute) { msg ->

                msg?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
                finish()

            }
        }


    }

}

