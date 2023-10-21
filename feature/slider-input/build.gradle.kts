plugins {
    alias(conventionPlugins.plugins.isevenapp.android.feature)
}

android {
    namespace = "com.example.sliderInput"
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:model"))
}