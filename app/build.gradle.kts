plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.hilt)
}

android {
    namespace = "com.example.interviewprep"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.interviewprep"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    val room_version = "2.7.0"
    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    // See Add the KSP plugin to your project
    ksp("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // GSON
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")

    implementation ("com.github.bumptech.glide:glide:4.12.0")

    // Glide v4 uses this new annotation processor -- see https://bumptech.github.io/glide/doc/generatedapi.html
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    implementation ("com.google.accompanist:accompanist-pager:0.13.0")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.13.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    implementation("androidx.navigation:navigation-compose:2.7.7")

    //dagger hilt dependencies
    implementation("com.google.dagger:hilt-android:2.56.2")
    ksp("com.google.dagger:hilt-android-compiler:2.56.2")

    //hilt compose nav dependencies
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")


    //new type-safe compose navigation
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}