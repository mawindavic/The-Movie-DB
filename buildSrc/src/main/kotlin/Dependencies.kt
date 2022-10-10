object AndroidLibraries {
    val splashScreen by lazy { "androidx.core:core-splashscreen:1.0.0-rc01" }
    val appCompat by lazy { "androidx.appcompat:appcompat:1.4.2" }
    val ktxCore by lazy { "androidx.core:core-ktx:1.8.0" }
    val material by lazy { "com.google.android.material:material:1.6.1" }
}

object GeneralViews {
    val constraint by lazy { "androidx.constraintlayout:constraintlayout:2.1.4" }
    val recycler by lazy { "androidx.recyclerview:recyclerview:1.2.1" }

}


object LiveCycleModel {
    val lifecycleExt by lazy { "androidx.lifecycle:lifecycle-extensions:2.2.0" }
    val livedata by lazy { "androidx.lifecycle:lifecycle-livedata-core-ktx:2.4.1" }
    val lifecycleViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1" }
    val fragment by lazy { "androidx.fragment:fragment-ktx:1.4.1" }
    val activity by lazy { "androidx.activity:activity-ktx:1.4.0" }

}

object Navigation {
    val common by lazy { "androidx.navigation:navigation-common-ktx:2.4.2" }
    val fragment by lazy { "androidx.navigation:navigation-fragment-ktx:2.4.2" }
    val ui by lazy { "androidx.navigation:navigation-ui-ktx:2.4.2" }
}


object Apache {
    val commons by lazy { "org.apache.commons:commons-text:1.9" }
}

object TestLibraries {
    val jUnitTest by lazy { "junit:junit:4.13.2" }
    val androidJUnitTest by lazy { "androidx.test.ext:junit:1.1.3" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:3.4.0" }
}


object Libraries {
    val timber by lazy { "com.jakewharton.timber:timber:5.0.1" }
    val coil by lazy { "io.coil-kt:coil:2.1.0" }
    val sweet by lazy { "com.github.f0ris.sweetalert:library:1.6.2" }

}

object Retrofit {
    val client by lazy { "com.squareup.retrofit2:retrofit:2.9.0" }
    val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:2.9.0" }
    val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3" }

}

object Security {
    val prefSec by lazy { "androidx.security:security-crypto-ktx:1.1.0-alpha03" }
}

object Room {
    val room by lazy { "androidx.room:room-ktx:2.4.2" }
    val roomRunTime by lazy { "androidx.room:room-runtime:2.4.2" }
    val roomSecurity by lazy { "net.zetetic:android-database-sqlcipher:4.5.0" }
    val roomKapt by lazy { "androidx.room:room-compiler:2.4.2" }


}

object Modules {
    val data by lazy { ":data" }
    val utils by lazy { ":utils" }
    val wakala by lazy { ":wakala" }
}

object Hilt {
    val hilt by lazy { "com.google.dagger:hilt-android:2.42" }
    val hiltKapt by lazy { "com.google.dagger:hilt-android-compiler:2.42" }

}