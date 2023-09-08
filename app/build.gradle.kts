@file:Suppress("UnstableApiUsage")

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("kapt")
    alias(libs.plugins.android.appication)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.shacklehotelbuddy"
    compileSdk = libs.versions.app.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = "com.example.shacklehotelbuddy"
        minSdk = libs.versions.app.sdk.min.get().toInt()
        targetSdk = libs.versions.app.sdk.target.get().toInt()
        versionCode = libs.versions.app.version.code.get().toInt()
        versionName = libs.versions.app.version.name.get()

        testInstrumentationRunner = "com.example.shacklehotelbuddy.HiltApplicationRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    @Suppress("UnstableApiUsage")
    buildTypes {
        named("release") {
            isMinifyEnabled = true
            setProguardFiles(listOf("proguard-android-optimize.txt", "proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(libs.kotlix.coroutines)
    implementation(libs.kotlix.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.logging.interceptor)
    implementation(libs.squareup.logcat)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.material3.android)
    androidTestImplementation(libs.androidx.test.runner)
    kapt(libs.androidx.room.compiler)
    implementation(libs.google.android.material)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.viewmodel)
    implementation(libs.androidx.compose.uiToolingPreview)
    implementation(libs.google.dagger.hilt.android)
    testImplementation(libs.google.dagger.hilt.android.testing)
    androidTestImplementation(libs.google.dagger.hilt.android.testing)
    kapt(libs.google.dagger.hilt.compiler)
    kaptTest(libs.google.dagger.hilt.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    testImplementation(libs.google.truth)
    androidTestImplementation(libs.google.truth)
    testImplementation(libs.kotlix.coroutines.test)
    androidTestImplementation(libs.kotlix.coroutines.test)
    testImplementation(libs.squareup.okhttp3.mockwebserver)
    testImplementation(libs.cash.turbine)
    androidTestImplementation(libs.cash.turbine)
    androidTestImplementation(libs.androidx.test.ext.junit)
    implementation(libs.io.coil.compose)
    implementation(libs.numberPicker)
}