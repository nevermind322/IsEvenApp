import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply(libs.plugins.android.library)
                apply(libs.plugins.kotlin.android)
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
        }
    }
}