import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.gradle.plugin.use.PluginDependency
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

const val IMPLEMENTATION = "implementation"
const val KAPT = "kapt"
internal fun Project.configureKotlinAndroid(ext: CommonExtension<*, *, *, *, *>) {
    with(ext) {
        compileSdk = 34
        defaultConfig.minSdk = 24

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
}

internal fun Project.configureCompose(ext: CommonExtension<*, *, *, *, *>) {
    ext.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
        }
    }

    dependencies {
        IMPLEMENTATION(platform(libs.androidx.compose.bom))
    }
}

internal fun PluginManager.apply(provider: Provider<PluginDependency>) =
    apply(provider.get().pluginId)
