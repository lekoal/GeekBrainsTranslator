plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.geekbrainstranslator"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.fragment:fragment-ktx:1.4.1")
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")

    implementation("androidx.core:core-splashscreen:1.0.0-rc01")

    //LIFECYCLE
    implementation(Dependencies.LIFECYCLE_LIVEDATA_DEPENDENCY)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_DEPENDENCY)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_SAVEDSTATE_DEPENDENCY)

    //Retrofit 2
    implementation(Dependencies.RETROFIT_DEPENDENCY)
    implementation(Dependencies.RETROFIT_JSON_CONVERTER_DEPENDENCY)
    implementation(Dependencies.RETROFIT_COROUTINES_ADAPTER_DEPENDENCY)

    //Broadcast manager
    implementation(Dependencies.BROADCAST_MANAGER_DEPENDENCY)

    //Koin
    implementation(Dependencies.KOIN_CORE_DEPENDENCY)
    implementation(Dependencies.KOIN_ANDROID_DEPENDENCY)
    implementation(Dependencies.KOIN_ANDROID_COMPAT_DEPENDENCY)

    //Coroutines
    implementation(Dependencies.COROUTINE_CORE_DEPENDENCY)
    implementation(Dependencies.COROUTINE_ANDROID_DEPENDENCY)

    //Room
    implementation(Dependencies.ROOM_RUNTIME_DEPENDENCY)
    kapt(Dependencies.ROOM_COMPILER_DEPENDENCY)
    implementation(Dependencies.ROOM_KTX_DEPENDENCY)

    //Glide
    implementation(Dependencies.GLIDE_DEPENDENCY)
    kapt(Dependencies.GLIDE_COMPILER_DEPENDENCY)

    //UTILS
    implementation(project(Dependencies.MY_UTILS))

    //DOMAIN
    implementation(project(Dependencies.DOMAIN))

    //DATA
    implementation(project(Dependencies.DATA))
}