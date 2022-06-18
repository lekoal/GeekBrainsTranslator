object Dependencies {
    private const val RETROFIT_VERSION = "2.9.0"
    private const val RETROFIT_COROUTINES_ADAPTER_VERSION = "0.9.2"
    private const val KOIN_VERSION = "3.2.0"
    private const val COROUTINE_VERSION = "1.6.2"
    private const val ROOM_VERSION = "2.4.2"
    private const val GLIDE_VERSION = "4.13.2"
    private const val BROADCAST_MANAGER_VERSION = "1.1.0"
    private const val LIFECYCLE_VERSION = "2.4.1"

    const val RETROFIT_DEPENDENCY =
        "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val RETROFIT_JSON_CONVERTER_DEPENDENCY =
        "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"
    const val KOIN_CORE_DEPENDENCY =
        "io.insert-koin:koin-core:$KOIN_VERSION"
    const val KOIN_ANDROID_DEPENDENCY =
        "io.insert-koin:koin-android:$KOIN_VERSION"
    const val KOIN_ANDROID_COMPAT_DEPENDENCY =
        "io.insert-koin:koin-android-compat:$KOIN_VERSION"
    const val COROUTINE_CORE_DEPENDENCY =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINE_VERSION"
    const val COROUTINE_ANDROID_DEPENDENCY =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINE_VERSION"
    const val ROOM_RUNTIME_DEPENDENCY =
        "androidx.room:room-runtime:$ROOM_VERSION"
    const val ROOM_COMPILER_DEPENDENCY =
        "androidx.room:room-compiler:$ROOM_VERSION"
    const val ROOM_KTX_DEPENDENCY =
        "androidx.room:room-ktx:$ROOM_VERSION"
    const val GLIDE_DEPENDENCY =
        "com.github.bumptech.glide:glide:$GLIDE_VERSION"
    const val GLIDE_COMPILER_DEPENDENCY =
        "com.github.bumptech.glide:compiler:$GLIDE_VERSION"
    const val BROADCAST_MANAGER_DEPENDENCY =
        "androidx.localbroadcastmanager:localbroadcastmanager:$BROADCAST_MANAGER_VERSION"
    const val RETROFIT_COROUTINES_ADAPTER_DEPENDENCY =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$RETROFIT_COROUTINES_ADAPTER_VERSION"
    const val LIFECYCLE_LIVEDATA_DEPENDENCY =
        "androidx.lifecycle:lifecycle-livedata-ktx:$LIFECYCLE_VERSION"
    const val LIFECYCLE_VIEWMODEL_DEPENDENCY =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"
    const val LIFECYCLE_VIEWMODEL_SAVEDSTATE_DEPENDENCY =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:$LIFECYCLE_VERSION"
}