package com.touhidapps.nav3_compose.route

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import androidx.navigationevent.compose.rememberNavigationEventDispatcherOwner
import androidx.savedstate.serialization.SavedStateConfiguration
import com.touhidapps.nav3_compose.screen.DetailScreen
import com.touhidapps.nav3_compose.screen.HomeScreen
import com.touhidapps.nav3_compose.screen.NameScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val config = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
          //  subclass(Route.AppHome::class, Route.AppHome.serializer())
            subclass(Route.Home::class, Route.Home.serializer())
            subclass(Route.NameScreen::class, Route.NameScreen.serializer())
            subclass(Route.DetailScreen::class, Route.DetailScreen.serializer())
            subclass(Route.Back::class, Route.Back.serializer())
            subclass(Route.Incorrect::class, Route.Incorrect.serializer())
        }
    }
}

val LocalNavigator = staticCompositionLocalOf<(Route) -> Unit> {
    error("Not provided")
}

@Composable
fun MainNavigation(
    route: Route = Route.Home(),
    finishNavActivity: (message: String?) -> Unit
) {

    val backStack = rememberNavBackStack(config,route)

    val popBackStack = {
        if (backStack.size == 1) {
            finishNavActivity(null)
        } else {
            backStack.removeLastOrNull()
        }
    }


    /**
     * BackHandler got priority if onBack and BackHandler both applied, BackHandler is not necessary for only compose single activity app
     * onBack can handle back navigation for single activity
     */
    BackHandler(enabled = true) {
        when(backStack.lastOrNull()) {
//            is Route.DetailScreen -> { // set your custom logic if necessary
//
//            }
//            is Route.NameScreen -> {
//
//            }
            else -> {
                popBackStack()
            }
        }
    }

    MaterialTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {

                    val dispatchOwner = rememberNavigationEventDispatcherOwner(enabled = true, parent = null)

                    CompositionLocalProvider(
                        LocalNavigationEventDispatcherOwner provides dispatchOwner,
                        LocalNavigator provides { route -> backStack.toRoute(route) { finishNavActivity(it) } }
                    ) {
                        NavDisplay(
                            backStack = backStack,
                            onBack = {
//                                when (backStack.lastOrNull()) {
////                                    is Route.DetailScreen -> {
////                                        println("Exiting from Detail Screen - onBack")
////                                        popBackStack()
////                                    }
////                                    is Route.NameScreen -> {
////
////                                    }
//                                    else -> {
//                                        popBackStack()
//                                    }
//                                }
                            },
                            entryProvider = entryProvider {
                              //  entry<Route.AppHome> { Box() {} }
                                entry<Route.Home> { key -> // can pass data also if you use Home class
                                    HomeScreen(key.data)
                                }
                                entry<Route.NameScreen> { key ->
                                    NameScreen(routeDataName = key.name)
                                }
                                entry<Route.DetailScreen> { key ->
                                    DetailScreen(routeData = key.data)
                                }
                            }
                        )
                    }
                }
            }
        }
    }

}


fun NavBackStack<NavKey>.toRoute(route: Route, finishNavActivity: (message: String?) -> Unit) {
//fun NavBackStack<NavKey>.toRoute(route: Route) {
    val backStack = this
    when(route) {
        is Route.Back -> {
            if (backStack.isNotEmpty()) {
                backStack.removeLastOrNull()
            }
        }
        is Route.AppHome -> {
            finishNavActivity("Finished compose activity")
        }
        is Route.Home -> {
            if (backStack.isNotEmpty()) {
                backStack.removeAll {
                    it !is Route.Home // when class must use is
//                    it != Route.Home
                }
            }
        }
        else -> {
            if (!backStack.contains(route)) {
                backStack.add(route)
            }
        }
    }
}

/**
 * Ex. Use in Nav drawer -
 * if (backStack.isItHome()) {
 * Set Hamburger IconButton
 * } else {
 * Set Back Arrow IconButton
 * }
 */
fun NavBackStack<NavKey>.isItHome(): Boolean {
    val backStack = this
    return backStack.size == 1 && backStack.first() == Route.Home
}
