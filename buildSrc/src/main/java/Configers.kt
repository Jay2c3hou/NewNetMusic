object Versions {
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"
}

object DependenciesConfig {
    const val CORE_KTX = "androidx.core:core-ktx:1.9.0"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:1.6.0"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.6.1"
    const val MATERIAL = "com.google.android.material:material:1.9.0"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.3"

    //Bmob
    const val BMOB_SDK = "io.github.bmob:android-sdk:3.9.4"
    const val RXJAVA = "io.reactivex.rxjava2:rxjava:2.2.8"
    const val RXANDROID = "io.reactivex.rxjava2:rxandroid:2.1.1"
    const val OK_HTTP = "com.squareup.okhttp3:okhttp:4.8.1"
    const val OKIO = "com.squareup.okio:okio:2.2.2"
    const val GSON = "com.google.code.gson:gson:2.8.5"

    //coroutine
    const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
    const val RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"

    //navigation
    const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:2.6.0"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:2.6.0"

    //viewModel
    const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:2.6.1"
    const val VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1"

    //RichText
    const val RICH_TEXT = "org.sufficientlysecure:html-textview:4.0"

    //StatusBarUtils
    const val STATUS_BAR_UTILS = "com.jaeger.statusbarutil:library:1.5.1"

    //Glide图片加载
    const val GLIDE = "com.github.bumptech.glide:glide:4.15.1"

    //EventBus
    const val EVENT_BUS = "org.greenrobot:eventbus:3.3.1"
}

object ModuleConfig {
    const val isModule = true  //作为一个开关
}