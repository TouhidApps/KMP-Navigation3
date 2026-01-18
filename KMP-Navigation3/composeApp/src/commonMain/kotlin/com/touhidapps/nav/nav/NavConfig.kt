package com.touhidapps.nav.nav

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
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