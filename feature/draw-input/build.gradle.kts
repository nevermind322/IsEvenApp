plugins {
    alias(conventionPlugins.plugins.isevenapp.android.feature)
}

android {
    namespace = "com.example.drawInput"

}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))

}