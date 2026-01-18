package com.touhidapps.nav3_compose.route

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic


val LocalNavigator = staticCompositionLocalOf<(Route) -> Unit> {
    error("Not provided")
}

@Immutable
data class ScreenEnv(
    val navigate: (Route) -> Unit,
    // add more like localization
)

@Composable
fun rememberScreenEnv(): ScreenEnv {
    return ScreenEnv(
        navigate = LocalNavigator.current,
        // add more like localization
    )
}

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