import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.test() {
    testImplementation(TestLibraries.jUnitTest)
    androidTestImplementation(TestLibraries.androidJUnitTest)
    androidTestImplementation(TestLibraries.espresso)
}

fun DependencyHandler.android() {
    implementation(AndroidLibraries.ktxCore)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.material)
    views()
}

fun DependencyHandler.views() {
    implementation(GeneralViews.constraint)
    implementation(GeneralViews.recycler)
}

fun DependencyHandler.navigation() {
    implementation(Navigation.common)
    implementation(Navigation.fragment)
    implementation(Navigation.ui)

}

fun DependencyHandler.lifecycle() {
    implementation(LiveCycleModel.lifecycleExt)
    implementation(LiveCycleModel.livedata)
    implementation(LiveCycleModel.lifecycleViewModel)
    implementation(LiveCycleModel.fragment)
}

fun DependencyHandler.retrofit() {
    implementation(Retrofit.client)
    implementation(Retrofit.gsonConverter)
    implementation(Retrofit.loggingInterceptor)
}

fun DependencyHandler.room() {
    implementation(Room.room)
    implementation(Room.roomRunTime)
    implementation(Room.roomSecurity)
    kapt(Room.roomKapt)
}

fun DependencyHandler.hilt() {
    implementation(Hilt.hilt)
    kapt(Hilt.hiltKapt)
}