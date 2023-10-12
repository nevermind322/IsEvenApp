import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {
            with(pluginManager){
                apply(librariesLibs.plugins.hilt)
                apply(librariesLibs.plugins.kotlin.kapt)
            }

            dependencies {
                IMPLEMENTATION(librariesLibs.hilt)
                KAPT(librariesLibs.hilt.compiler)
            }

            extensions.getByType<KaptExtension>().apply {
                correctErrorTypes = true
            }
        }

    }

}