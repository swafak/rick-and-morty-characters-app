plugins {
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.jetbrains.kotlin.android)
//    id("kotlin-kapt")
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    kapt {
        correctErrorTypes = true
    }

    namespace = "com.example.rick_and_morty_characters_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rick_and_morty_characters_app"
        minSdk = 24
        targetSdk = 34
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

    defaultConfig {
        multiDexEnabled = true
    }
    buildFeatures {
        viewBinding = true
      dataBinding = true
    }
}

dependencies {

    // Retrofit dependencies
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Shimmer dependency
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // Room dependencies
    implementation("androidx.room:room-runtime:2.4.3")
    implementation("androidx.room:room-ktx:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")


    //Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    kapt ("com.github.bumptech.glide:compiler:4.12.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    //hilt
    implementation("com.google.dagger:hilt-android:2.44")
     kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

     // Retrofit dependencies
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Network Response dependency
    implementation("com.github.haroldadmin:NetworkResponseAdapter:4.0.1")


    //paging
    val paging_version = "2.1.2"

    implementation("androidx.paging:paging-runtime:$paging_version")
    // alternatively - without Android dependencies for testing
    testImplementation("androidx.paging:paging-common:$paging_version")
    // optional - RxJava support
    implementation("androidx.paging:paging-rxjava2:$paging_version")


    //instrumented tests
    //assertion library
    androidTestImplementation("com.google.truth:truth:1.4.4")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}