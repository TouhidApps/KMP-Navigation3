package com.touhidapps.nav.nav

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.touhidapps.nav.screen.HomeScreen
import com.touhidapps.nav.screen.alertEntry
import com.touhidapps.nav.screen.homeEntry
import com.touhidapps.nav.screen.listDetailEntry
import com.touhidapps.nav.screen.listEntry
import com.touhidapps.nav.screen.userDetailEntry
import com.touhidapps.nav.screen.userEntry


@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MainNavigation() {

    val backStack = rememberNavBackStack(config, Route.Home)

    // Override the defaults so that there isn't a horizontal space between the panes.
    // See b/418201867
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val directive = remember(windowAdaptiveInfo) {
        calculatePaneScaffoldDirective(windowAdaptiveInfo).copy(horizontalPartitionSpacerSize = 0.dp)
    }

    // First try 2 panel strategy _if doesn't support use ListDetail Strategy if it also doesn't support automatically use SinglePaneSceneStrategy
    val strategy =
        rememberTwoPaneSceneStrategy<NavKey>() then rememberListDetailSceneStrategy<NavKey>(
            directive = directive
        ) then remember { DialogSceneStrategy<NavKey>() }

    MaterialTheme {
        CompositionLocalProvider(
            LocalNavigator provides { route -> backStack.toRoute(route) }
        ) {

            val env = rememberScreenEnv() // use it inside CompositionLocalProvider


            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {


                        NavDisplay(
                            backStack = backStack,
                            sceneStrategy = strategy,
                            onBack = {
                                env.navigate(Route.Back)
                            },
                            // In order to add the `ViewModelStoreNavEntryDecorator` (see comment below for why)
                            // we also need to add the default `NavEntryDecorator`s as well. These provide
                            // extra information to the entry's content to enable it to display correctly
                            // and save its state.
                            //                    entryDecorators = listOf( // when use viewmodel
                            //                        rememberSaveableStateHolderNavEntryDecorator(),
                            //                        rememberViewModelStoreNavEntryDecorator()
                            //                    ),
                            entryProvider = entryProvider {

                                homeEntry()
                                userEntry()
                                userDetailEntry()
                                listEntry()
                                listDetailEntry()
                                alertEntry()

                            },
                            transitionSpec = {
                                // Slide in from right when navigating forward
                                slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(
                                    targetOffsetX = { -it })
                            },
                            popTransitionSpec = {
                                // Slide in from left when navigating back
                                slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(
                                    targetOffsetX = { it })
                            },
                            predictivePopTransitionSpec = {
                                // Slide in from left when navigating back
                                slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(
                                    targetOffsetX = { it })
                            }
                        )
                    }


                }
            }
        }
    }


}





