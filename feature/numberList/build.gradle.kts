@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(conventionPlugins.plugins.isevenapp.android.feature)
}

android {
    namespace = "com.example.listInput"
}