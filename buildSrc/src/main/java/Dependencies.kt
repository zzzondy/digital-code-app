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

        const val navigation = "androidx.navigation:navigation-compose:2.7.5"
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

    object Firebase {

        const val firebaseBom = "com.google.firebase:firebase-bom:32.5.0"

        const val firestore = "com.google.firebase:firebase-firestore"
    }

    object Hilt {

        private const val hiltVersion = "2.48.1"

        const val daggerHiltProject = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"

        const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        const val hiltCompose = "androidx.hilt:hilt-navigation-compose:1.1.0"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:1.1.0"
    }

    object Paging {
        private const val pagingVersion = "3.2.1"

        const val runtime = "androidx.paging:paging-runtime:$pagingVersion"
        const val compose = "androidx.paging:paging-compose:3.3.0-alpha02"
    }

}