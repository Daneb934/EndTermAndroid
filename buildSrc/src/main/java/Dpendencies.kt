import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 26
    const val compileSdk = 30
    const val targetSdk = 30
    val javaVersion = JavaVersion.VERSION_1_8
    const val buildTools = "29.0.3"
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val androidx_appcompat = "1.1.0"
    const val android_core = "1.3.0"
    const val androidx_navigation = "2.3.0-rc01"
    const val androidx_constraintLayout = "1.1.3"
    const val androidx_material = "1.1.0"

    //lifecycle
    const val lifecycle = "2.2.0"
    const val lifecycle_test = "2.1.0"

    // LiveEvent
    const val live_event = "1.2.0"


    //coroutines
    const val coroutines = "1.3.3"

    ///groupie
    const val groupie = "2.8.1"

    //fragment ktx
    const val fragment_ktx = "1.2.5"

    //glide
    const val glide = "4.11.0"

    //paging
    const val paging = "2.1.2"

    //networking
    const val restofit = "2.7.0"
    const val okhttp3 = "4.2.2"

    //room
    const val room = "2.2.3"

    //swipe reflesh layout
    const val swiperefreshlayout = "1.0.0"

    //log
    const val timber = "4.7.1"

    //di
    const val koin_version = "2.1.5"
    const val koin_test = "2.0.1"

    // <editor-fold desc="testing">
    const val junit = "4.13"
    const val junit_ext = "1.1.1"
    const val androidx_espresso = "3.1.0"
    const val androidx_testing = "1.1.1"

    // <editor-fold desc="tools">
    const val gradleandroid = "4.0.0"
    const val gradleversions = "0.21.0"
    const val kotlin = "1.3.72"
    // </editor-fold>
}

object Deps {
    const val androidx_core = "androidx.core:core-ktx:${Versions.android_core}"
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"

    // Coroutines
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val androidx_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val fragment_ktx =
        "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"

    //Glide
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    //Lifecycle
    const val lifecycle_process = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle}"
    const val lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycle_test = "androidx.arch.core:core-testing:${Versions.lifecycle_test}"

    const val live_event = "com.github.hadilq.liveevent:liveevent:${Versions.live_event}"

    // Constraint layout
    const val androidx_constraint =
        "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintLayout}"

    //Groupie
    const val groupie = "com.xwray:groupie:${Versions.groupie}"
    const val groupie_viewbinding = "com.xwray:groupie-viewbinding:${Versions.groupie}"

    // Timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // Koin
    const val androidx_koin = "org.koin:koin-android:${Versions.koin_version}"
    const val koin_scope = "org.koin:koin-androidx-scope:${Versions.koin_version}"
    const val koin_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin_version}"
    const val koin_test = "org.koin:koin-test:${Versions.koin_test}"

    //Room
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"

    // Networking
    const val retroift = "com.squareup.retrofit2:retrofit:${Versions.restofit}"
    const val retrofit_converter = "com.squareup.retrofit2:converter-gson:${Versions.restofit}"
    const val okhttp3 = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"

    // Navigation
    const val navigation_fragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}"
    const val navigation_ui =
        "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}"

    const val save_args =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidx_navigation}"

    // Material
    const val material = "com.google.android.material:material:${Versions.androidx_material}"

    //Paging
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    //swiperefreshlayout
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"

    const val testlib_junit = "junit:junit:${Versions.junit}"
    const val testext_junit = "androidx.test.ext:junit:${Versions.junit_ext}"

    const val testandroidx_rules = "androidx.test:rules:${Versions.androidx_testing}"
    const val testandroidx_runner = "androidx.test:runner:${Versions.androidx_testing}"
    const val testandroidx_espressocore =
        "androidx.test.espresso:espresso-core:${Versions.androidx_espresso}"

    const val tools_gradleandroid = "com.android.tools.build:gradle:${Versions.gradleandroid}"
    const val tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val tools_kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val tools_gradleversions =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleversions}"
}