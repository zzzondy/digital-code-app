plugins {
    id(Plugins.kotlinAndroid)
    id(Plugins.androidApplication)
    id(Plugins.gmsGoogleServices)
    kotlin(Plugins.kapt)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.digitalcodeapp"
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.composeVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Lifecycle
    implementation(Dependencies.Lifecycle.core)
    implementation(Dependencies.Lifecycle.lifecycleRuntime)
    implementation(Dependencies.Lifecycle.composeActivity)

    // Compose
    implementation(platform(Dependencies.Compose.composeBom))
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiGraphics)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Compose.material3)
    debugImplementation(Dependencies.Compose.tooling)
    debugImplementation(Dependencies.Compose.testManifest)

    // Navigation
    implementation(Dependencies.Compose.navigation)

    // Firebase
    implementation(platform(Dependencies.Firebase.firebaseBom))

    // Hilt
    implementation(Dependencies.Hilt.hilt)
    implementation(Dependencies.Hilt.hiltCompose)
    kapt(Dependencies.Hilt.hiltAndroidCompiler)
    kapt(Dependencies.Hilt.hiltCompiler)

    // Testing
    testImplementation(Dependencies.Testing.junit)
    androidTestImplementation(Dependencies.Testing.androidJunit)
    androidTestImplementation(Dependencies.Testing.espresso)
    androidTestImplementation(platform(Dependencies.Compose.composeBom))
    androidTestImplementation(Dependencies.Testing.composeJunit)
}

kapt {
    correctErrorTypes = true
}