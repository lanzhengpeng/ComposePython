plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.chaquo.python") version "16.1.0"
}

android {
    namespace = "com.example.composepythondemo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.composepythondemo"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // 添加 ndk.abiFilters 配置

        // 添加 NDK ABI 配置
        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64") // 根据你的需求选择支持的架构
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    // Chaquopy 配置
    chaquopy {
        defaultConfig {
            version = "3.8" // 指定 Python 版本（可选 3.8、3.9、3.11 等）
            pip {
                install("requests") // 安装 Python requests 库
                // 可添加其他 Python 包，例如：
                // install("numpy")
                // install("-r", "requirements.txt")
            }
        }
    }
}
//chaquopy {
//    // 设置 Python 源码目录
//    source(file("src/main/python"))
//
//    // 配置 pip 依赖
//    pip {
//        install("numpy")
//        install("pandas")
//    }
//}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    //添加Jetpack Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //添加navigation管理页面
    implementation(libs.androidx.navigation.compose)
    //运行时依赖
    //implementation("com.chaquo.python:chaquopy-api:16.1.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


}