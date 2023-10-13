import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(conventionPlugins.isevenapp.android.library.compose)
                apply(conventionPlugins.isevenapp.android.hilt)
            }

            dependencies {

                IMPLEMENTATION(libs.androidx.compose.ui)
                IMPLEMENTATION(libs.androidx.compose.ui.graphics)
                IMPLEMENTATION(libs.androidx.compose.ui.tooling.preview)
                IMPLEMENTATION(libs.androidx.compose.material3)
                IMPLEMENTATION(libs.androidx.compose.navigation)

                IMPLEMENTATION(libs.androidx.lifecycle.viewmodel.ktx)
                IMPLEMENTATION(libs.androidx.lifecycle.viewmodel.ktx)
            }
        }
    }
}