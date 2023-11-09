@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(conventionPlugins.plugins.isevenapp.android.library)
    alias(conventionPlugins.plugins.isevenapp.android.hilt)
}


android {
    namespace = "com.example.digitClassifier"
    androidResources {
        noCompress.add("tflite")
    }
}

dependencies {
    implementation("org.tensorflow:tensorflow-lite:2.5.0")

}