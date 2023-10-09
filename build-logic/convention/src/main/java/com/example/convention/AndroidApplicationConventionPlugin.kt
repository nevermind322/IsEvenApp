import com.android.build.api.dsl.ApplicationExtension
import com.example.convention.apply
import com.example.convention.configureKotlinAndroid
import com.example.convention.librariesLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure


class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(librariesLibs.plugins.android.application)
                apply(librariesLibs.plugins.kotlin.android)
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
            }
        }
    }
}


