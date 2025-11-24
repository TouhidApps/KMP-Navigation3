package com.touhidapps.nav.nav

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.touhidapps.nav.screen.HomeScreen
import com.touhidapps.nav.screen.ListDetailScreen
import com.touhidapps.nav.screen.ListScreen
import com.touhidapps.nav.screen.UserDetailScreen
import com.touhidapps.nav.screen.UserScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic


val config = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Route.Home::class, Route.Home.serializer())
            subclass(Route.User::class, Route.User.serializer())
            subclass(Route.UserDetail::class, Route.UserDetail.serializer())
            subclass(Route.List::class, Route.List.serializer())
            subclass(Route.ListDetail::class, Route.ListDetail.serializer())
            subclass(Route.MyDialog::class, Route.MyDialog.serializer())
            subclass(Route.Back::class, Route.Back.serializer())
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MainNavigation() {

    val backStack = rememberNavBackStack(config, Route.Home)

    fun doRoute(route: Route) {
        when(route) {
            is Route.Back -> {
                if (backStack.isNotEmpty()) {
                    backStack.removeLastOrNull()
                }
            }
            is Route.Home -> {
                if (backStack.isNotEmpty()) {
                    backStack.removeAll { it != Route.Home }
                }
            }
            else -> {
                if (!backStack.contains(route)) {
                    backStack.add(route)
                }
            }
        }
    }

    // Override the defaults so that there isn't a horizontal space between the panes.
    // See b/418201867
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val directive = remember(windowAdaptiveInfo) {
        calculatePaneScaffoldDirective(windowAdaptiveInfo).copy(horizontalPartitionSpacerSize = 0.dp)
    }

    // First try 2 panel strategy if doesn't support use ListDetail Strategy if it also doesn't support automatically use SinglePaneSceneStrategy
    val strategy = rememberTwoPaneSceneStrategy<NavKey>() then rememberListDetailSceneStrategy<NavKey>(directive = directive) then remember { DialogSceneStrategy<NavKey>() }

    val navAnimation = NavDisplay.transitionSpec {
        // Slide new content up, keeping the old content in place underneath
        slideInVertically(initialOffsetY = { it }, animationSpec = tween(1000)) togetherWith ExitTransition.KeepUntilTransitionsFinished
    } + NavDisplay.popTransitionSpec {
        // Slide old content down, revealing the new content in place underneath
        EnterTransition.None togetherWith slideOutVertically(targetOffsetY = { it }, animationSpec = tween(1000))
    } + NavDisplay.predictivePopTransitionSpec {
        // Slide old content down, revealing the new content in place underneath
        EnterTransition.None togetherWith slideOutVertically(targetOffsetY = { it }, animationSpec = tween(1000))
    }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {

                    CompositionLocalProvider() {
                        NavDisplay(
                            backStack = backStack,
                            sceneStrategy = strategy,
                            onBack = {
                                doRoute(Route.Back)
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

                                entry<Route.Home>(
                                    metadata = TwoPaneScene.twoPane() // (Landscape) After clicking a button from home
                                ) { key ->
                                    HomeScreen() { route ->
                                        doRoute(route)
                                    }
                                }

                                entry<Route.User>(
                                    metadata = TwoPaneScene.twoPane()
                                ) { key ->

                                    UserScreen(routeDataName = key.name) { route ->
                                        doRoute(route)
                                    }

                                }

                                entry<Route.UserDetail>(
                                    // To use multiple metadata use + sign
                                    metadata = TwoPaneScene.twoPane() + navAnimation
                                ) { key ->
                                    UserDetailScreen(routeData = key.data) { route ->
                                        doRoute(route)
                                    }
                                }

                                entry<Route.List>(
                                    metadata = ListDetailSceneStrategy.listPane(
                                        detailPlaceholder = {
                                            Text("Choose an item from the list")
                                        }
                                    )
                                ) { key ->
                                    ListScreen() { route ->
                                        doRoute(route)
                                    }
                                }

                                entry<Route.ListDetail>(
                                    metadata = ListDetailSceneStrategy.detailPane()
                                ) { key ->
                                    ListDetailScreen(key.flowerName) { route ->
                                        doRoute(route)
                                    }
                                }

                                entry<Route.MyDialog>(
                                    metadata = DialogSceneStrategy.dialog(
                                        DialogProperties(
                                            dismissOnBackPress = true,
                                            dismissOnClickOutside = false,
                                        )
                                    )
                                ) { key ->
                                    Column(
                                        modifier = Modifier.background(color = Color.White)
                                    ) {
                                        Text("This is an alert")
                                        Button(onClick = {
                                            doRoute(Route.Back)
                                        }) {
                                            Text("Done")
                                        }
                                    }
                                }


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










