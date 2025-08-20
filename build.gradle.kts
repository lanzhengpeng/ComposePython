// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.chaquo.python") version "16.1.0" apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://repo1.maven.org/maven2/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.2") // 你的 Android Gradle 版本
        classpath("com.chaquo.python:gradle:16.1.0")  // 使用 Maven Central 中存在的版本
    }
}
