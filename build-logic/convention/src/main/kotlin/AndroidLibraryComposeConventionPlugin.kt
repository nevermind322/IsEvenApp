import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {
            with(pluginManager) {
                apply(conventionPlugins.isevenapp.android.library.asProvider())
            }
            val ext = extensions.getByType<LibraryExtension>()
            configureCompose(ext)
        }

    }

}