plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.effectivemobiletestismaev"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.effectivemobiletestismaev"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "JOBS_API_BASE_URL",
            "\"https://drive.usercontent.google.com\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.di.koin)

    implementation(project(":core:uikit"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":features:favorite"))
    implementation(project(":features:messages"))
    implementation(project(":features:profile"))
    implementation(project(":features:responses"))
    implementation(project(":features:search"))
    implementation(project(":features:vacancy_detail"))
}