plugins {

    alias(conventionPlugins.plugins.isevenapp.android.feature)
}

android {
    namespace = "com.example.isevenapp.feature.keyboardInput"
}
dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))
}
