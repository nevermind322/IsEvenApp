import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`

}

group = "com.example.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}
dependencies {
    implementation(files("../.gradle/a7c91e4ceb8bf5980fd7b2d15a24507b8ff40fc5/classes/org/gradle/accessors/dm/LibrariesForLibs.kt"))
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    compileOnly("com.android.tools.build:gradle:8.1.1")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    compileOnly("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.9.0-1.0.13")
}

gradlePlugin {
    plugins {
        register("AndroidApplication") {
            id = "isevenapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("AndroidLibrary") {
            id = "isevenapp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("AppCompose"){
            id = "isevenapp.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("LibraryCompose") {
            id  = "isevenapp.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
    }
}