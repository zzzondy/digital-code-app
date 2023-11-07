object Dependencies {

    object Compose {
        const val composeVersion = "1.5.3"

        const val composeBom = "androidx.compose:compose-bom:2023.10.01"

        const val ui = "androidx.compose.ui:ui"
        const val uiGraphics = "androidx.compose.ui:ui-graphics"
        const val material3 = "androidx.compose.material3:material3"
        const val tooling = "androidx.compose.ui:ui-tooling"
        const val testManifest = "androidx.compose.ui:ui-test-manifest"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
    }

    object Lifecycle {

        const val core = "androidx.core:core-ktx:1.12.0"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
        const val composeActivity = "androidx.activity:activity-compose:1.8.0"
    }

    object Testing {

        const val junit = "junit:junit:4.13.2"
        const val androidJunit = "androidx.test.ext:junit:1.1.5"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4"
    }

}