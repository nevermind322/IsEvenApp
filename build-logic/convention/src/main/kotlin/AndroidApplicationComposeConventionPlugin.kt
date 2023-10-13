import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(conventionPlugins.isevenapp.android.application.asProvider())
            }

            val ext = extensions.getByType<ApplicationExtension>()
            configureCompose(ext)
        }
    }

}