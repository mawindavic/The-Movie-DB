plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinParcelize)
    id(Plugins.hilt)
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.mawinda.data"
    compileSdk = AndroidSdkConfig.compileSdk

    defaultConfig {
        minSdk = AndroidSdkConfig.minSdk
        targetSdk = AndroidSdkConfig.targetSdk
        testInstrumentationRunner = AndroidSdkConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.18.1"
        }
    }
}

dependencies {
    retrofit()
    implementation(Libraries.timber)
    room()
    hilt()
    paging()
}
kapt {
    correctErrorTypes = true
}