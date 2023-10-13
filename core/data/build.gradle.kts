plugins {
    alias(conventionPlugins.plugins.isevenapp.android.library)
    alias(conventionPlugins.plugins.isevenapp.android.hilt)
}

android {
    namespace = "com.example.core.data"
}

dependencies {

    implementation(project(":core:network"))
}