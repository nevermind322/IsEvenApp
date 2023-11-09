plugins {
    alias(conventionPlugins.plugins.isevenapp.android.feature)
}

android {
    namespace = "com.example.drawInput"

}

dependencies {
    implementation(project(":core:domain"))
}