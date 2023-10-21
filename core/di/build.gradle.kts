@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(conventionPlugins.plugins.isevenapp.android.library)
    alias(conventionPlugins.plugins.isevenapp.android.hilt)

}

android {
    namespace = "com.example.di"
}
dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:network"))
}
