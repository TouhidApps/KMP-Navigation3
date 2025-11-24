This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop (JVM).

# KMP Navigation3 Example

## Libraries:

```
[versions]
androidx-nav3 = "1.0.0-alpha05"
androidx-adaptive = "1.3.0-alpha02"
kotlinx-serialization = "1.9.0"

[libraries]
androidx-lifecycle-viewmodel-nav3 = { module = "org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-navigation3", version.ref = "androidx-lifecycle" }
kotlinx-serialization-json = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json", version.ref = "kotlinx-serialization" }
androidx-nav3-ui = { module = "org.jetbrains.androidx.navigation3:navigation3-ui", version.ref = "androidx-nav3" }
androidx-material3-adaptive = { module = "org.jetbrains.compose.material3.adaptive:adaptive", version.ref = "androidx-adaptive" }
androidx-material3-adaptive-nav3 = { module = "org.jetbrains.compose.material3.adaptive:adaptive-navigation3", version.ref = "androidx-adaptive" }

[plugins]
kotlinx-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
```

### Add to gradle:
```
alias(libs.plugins.kotlinx.serialization)

implementation(libs.androidx.lifecycle.viewmodel.nav3)
implementation(libs.kotlinx.serialization.json)
implementation(libs.androidx.nav3.ui)
implementation(libs.androidx.material3.adaptive)
implementation(libs.androidx.material3.adaptive.nav3)
```
