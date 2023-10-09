import com.android.build.api.dsl.ApplicationExtension
import com.example.convention.apply
import com.example.convention.configureCompose
import com.example.convention.librariesLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin  : Plugin<Project>{

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(librariesLibs.plugins.android.application)

            val ext = extensions.getByType<ApplicationExtension>()
            configureCompose(ext)
        }
    }

}