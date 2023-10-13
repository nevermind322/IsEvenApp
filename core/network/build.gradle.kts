plugins {
    alias(conventionPlugins.plugins.isevenapp.android.library)
    alias(conventionPlugins.plugins.isevenapp.android.hilt)
}

android {
    namespace = "com.example.network"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(project(":core:model"))
}