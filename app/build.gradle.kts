import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp").version("1.8.0-1.0.9")
}

android {
    namespace = "com.example.nexttogo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nexttogo"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("${Libs.ANDROID_CORE_KTX}:${Versions.ANDROID_CORE_KTX}")
    implementation("${Libs.ANDROIDX_MATERIAL}:${Versions.ANDROIDX_MATERIAL}")
    implementation("${Libs.MATERIAL_ICONS}:${Versions.ANDROIDX_MATERIAL}")
    implementation("${Libs.ANDROIDX_MATERIAL3}:${Versions.ANDROIDX_MATERIAL3}")
    implementation("${Libs.LIFECYCLE_RUNTIME_KTX}:${Versions.LIFECYCLE_RUNTIME_KTX}")
    implementation("${Libs.ACTIVITY_COMPOSE}:${Versions.ACTIVITY_COMPOSE}")
    implementation("${Libs.SYSTEM_UI_CONTROLLER}:${Versions.SYSTEM_UI_CONTROLLER}")

    implementation(platform("${Libs.COMPOSE_BOOM}:${Versions.COMPOSE_BOOM}"))
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_UI_GRAPHIC)
    implementation(Libs.COMPOSE_UI_TOOL_PREVIEW)
    implementation("${Libs.CONSTRAINT_LAYOUT}:${Versions.CONSTRAINT_LAYOUT}")
    implementation("${Libs.NAVIGATION_COMPOSE}:${Versions.NAVIGATION_COMPOSE}")
    testImplementation("${Libs.JUNIT}:${Versions.JUNIT}")
    androidTestImplementation("${Libs.EXT_JUNIT}:${Versions.EXT_JUNIT}")
    androidTestImplementation("${Libs.ESPRESSO_CORE}:${Versions.ESPRESSO_CORE}")
    androidTestImplementation(platform("${Libs.COMPOSE_BOOM}:${Versions.COMPOSE_BOOM}"))
    debugImplementation(Libs.COMPOSE_UI_TOOLING)
    debugImplementation(Libs.COMPOSE_MANIFEST_TEST)
    implementation("${Libs.LIFECYCLE}:${Versions.LIFECYCLE_EXTENSION}")
    implementation("${Libs.LIFECYCLE_LIVE_DATA_KTX}:${Versions.LIFECYCLE_VERSION}")
    implementation("${Libs.LIFECYCLE_RUNTIME_KTX}:${Versions.LIFECYCLE_VERSION}")
    implementation("${Libs.COMPOSE_LIVEDATA}:${Versions.COMPOSE_LIVEDATA_VERSION}")

    // Hilt dependencies
    implementation("${Libs.DAGGER_HILT_ANDROID}:${Versions.DAGGER_HILT_VERSION}")
    kapt("${Libs.DAGGER_HILT_ANDROID_COMPILER}:${Versions.DAGGER_HILT_VERSION}")
    implementation("${Libs.HILT_NAVIGATION_COMPOSE}:${Versions.HILT_NAVIGATION_COMPOSE}")

    //Network
    implementation("${Libs.SQUARE_UP_MOSHI_KOTLIN}:${Versions.SQUARE_UP_MOSHI}")
    implementation("${Libs.SQUARE_UP_OKHTTP3}:${Versions.SQUARE_UP_OKHTTP3}")
    ksp("${Libs.SQUARE_UP_MOSHI_CODEGEN}:${Versions.SQUARE_UP_MOSHI}")
    implementation("${Libs.SQUARE_UP_OKHTTP3_INTERCEPTOR}:${Versions.SQUARE_UP_OKHTTP3}")
    implementation("${Libs.SQUARE_UP_RETROFIT2_CONVERTER}:${Versions.RETROFIT_VERSION}")
    implementation("${Libs.SQUARE_UP_RETROFIT2}:${Versions.RETROFIT_VERSION}")

    //moshi
    implementation("${Libs.SQUARE_UP_MOSHI}:${Versions.SQUARE_UP_MOSHI}")
    ksp("${Libs.SQUARE_UP_MOSHI_CODEGEN}:${Versions.SQUARE_UP_MOSHI}")
    implementation("${Libs.SQUARE_UP_MOSHI_KOTLIN}:${Versions.SQUARE_UP_MOSHI}")

    //Unit test
    testImplementation("${Libs.COROUTINES_TEST}:${Versions.COROUTINES_TEST}")
    androidTestImplementation("${Libs.COMPOSE_UI_TEST}:${Versions.COMPOSE_VERSION}")
    testImplementation("${Libs.MOCKITO_KOTLIN}:${Versions.MOCKITO_KOTLIN}")
    testImplementation("${Libs.MOCKK}:${Versions.MOCKK}")

}