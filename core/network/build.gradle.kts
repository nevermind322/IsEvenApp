plugins {
    alias(appPlugins.plugins.isevenapp.android.library)
    alias(appPlugins.plugins.isevenapp.android.hilt)
}

android {
    namespace = "com.example.network"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.moshi)
}