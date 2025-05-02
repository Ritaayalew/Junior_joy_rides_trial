//plugins {
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.kotlin.compose)
//    // Reason: The Compose compiler plugin is not needed since Kotlin 1.8.20+; the composeOptions block is sufficient.
//    alias(libs.plugins.hilt.android) // Changed: Used alias from libs.versions.toml instead of id("dagger.hilt.android.plugin")
//    alias(libs.plugins.ksp) // Changed: Used alias from libs.versions.toml instead of id("com.google.devtools.ksp")
//    alias(libs.plugins.kotlin.kapt) // Changed: Used alias from libs.versions.toml instead of id("kotlin-kapt")
//}
//
//android {
//    namespace = "com.example.trial_junior"
//    compileSdk = 35
//
//    defaultConfig {
//        applicationId = "com.example.trial_junior"
//        minSdk = 24
//        targetSdk = 35
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_17
//        targetCompatibility = JavaVersion.VERSION_17
//    }
//
//    kotlinOptions {
//        jvmTarget = "17"
//    }
//
//    buildFeatures {
//        compose = true
//    }
//
//    // Added: Specify the Compose Compiler version
//    composeOptions {
//        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
//    }
//}
//
//dependencies {
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
//    implementation(libs.androidx.hilt.navigation.compose) // Added: Moved from manual dependency to libs.versions.toml
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)
//
//    // Removed: Manual Compose dependencies (already managed by BOM)
//    // implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")
//    // implementation("androidx.navigation:navigation-compose:2.8.0")
//    // implementation("androidx.compose.material:material-icons-extended:1.7.0")
//    // implementation("androidx.hilt:hilt-navigation-compose:1.2.1")
//
//    // Dagger-Hilt
//    implementation(libs.hilt.android) // Changed: Used libs.versions.toml
//    kapt(libs.hilt.compiler) // Changed: Used libs.versions.toml
//    kapt(libs.androidx.hilt.compiler) // Changed: Used libs.versions.toml
//
//    // Coroutines
//    implementation(libs.kotlinx.coroutines.core) // Changed: Used libs.versions.toml
//    implementation(libs.kotlinx.coroutines.android) // Changed: Used libs.versions.toml
//
//    // Room
//    implementation(libs.androidx.room.ktx)
//    implementation(libs.androidx.room.runtime)
//    ksp(libs.androidx.room.compiler)
//
//    // Removed: Duplicate Room dependency
//    // implementation("androidx.room:room-ktx:2.6.1")
//
//    // Retrofit
//    implementation(libs.retrofit) // Changed: Used libs.versions.toml
//
//    // Gson
//    implementation(libs.gson) // Changed: Used libs.versions.toml
//    implementation(libs.retrofit.converter.gson) // Changed: Used libs.versions.toml
//
//    // Add this line for extended icons
//    implementation("androidx.compose.material:material-icons-extended")
//}


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    // Removed: kapt plugin since we're migrating Hilt to ksp
}

android {
    namespace = "com.example.trial_junior"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.trial_junior"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.hilt.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dagger-Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler) // Migrated from kapt to ksp
    ksp(libs.androidx.hilt.compiler) // Migrated from kapt to ksp

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // Retrofit
    implementation(libs.retrofit)

    // Gson
    implementation(libs.gson)
    implementation(libs.retrofit.converter.gson)

    //logging
    implementation(libs.okhttp.logging.interceptor)  // Added for Retrofit logging
}