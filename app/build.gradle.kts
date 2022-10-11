import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinParcelize)
    id(Plugins.navigation)
    id(Plugins.hilt)
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.mawinda.themoviedb"
    compileSdk = AndroidSdkConfig.compileSdk

    defaultConfig {
        applicationId = AndroidSdkConfig.appId
        minSdk = AndroidSdkConfig.minSdk
        targetSdk = AndroidSdkConfig.targetSdk
        versionCode = AndroidSdkConfig.versionCode
        versionName = AndroidSdkConfig.versionName

        testInstrumentationRunner = AndroidSdkConfig.testInstrumentationRunner
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    android()
    navigation()
    lifecycle()
    implementation(AndroidLibraries.splashScreen)
    implementation(Libraries.coil)
    implementation(Libraries.timber)
    hilt()
    implementation(project(Modules.data))
    test()
}

kapt {
    correctErrorTypes = true
}