package com.touhidapps.nav3_compose.route

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import androidx.navigationevent.compose.rememberNavigationEventDispatcherOwner
import com.touhidapps.nav3_compose.screen.detailEntry
import com.touhidapps.nav3_compose.screen.homeEntry
import com.touhidapps.nav3_compose.screen.nameEntry


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

                                homeEntry()
                                nameEntry()
                                detailEntry()

                            }
                        )
                    }
                }
            }
        }
    }

}


