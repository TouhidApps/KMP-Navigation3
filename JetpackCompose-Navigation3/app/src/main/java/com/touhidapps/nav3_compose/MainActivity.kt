package com.touhidapps.nav3_compose

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.touhidapps.nav3_compose.model.UserData
import com.touhidapps.nav3_compose.route.Route
import com.touhidapps.nav3_compose.route.goToComposeActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        findViewById<MaterialButton>(R.id.btnOpenComposeActivity).setOnClickListener {

            val homeData = Route.Home.Data(
                userData = UserData(
                    userName = "Touhid",
                    userPhone = "01283948"
                ),
                randomId = Random.nextInt(1000, 5000)
            )

            goToComposeActivity(Route.Home(homeData), this@MainActivity)

        }

    }
}