plugins {
    id("isevenapp.android.library")
    id("isevenapp.android.library.compose")
}

android {
    namespace = "com.example.isevenapp.feature.keyboardInput"
}

dependencies {
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
}