import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {
            with(pluginManager){
                apply(libs.plugins.hilt)
                apply(libs.plugins.kotlin.kapt)
            }

            dependencies {
                IMPLEMENTATION(libs.hilt)
                KAPT(libs.hilt.compiler)
            }

            extensions.getByType<KaptExtension>().apply {
                correctErrorTypes = true
            }
        }

    }

}