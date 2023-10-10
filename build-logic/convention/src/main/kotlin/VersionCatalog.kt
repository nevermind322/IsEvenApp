import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.the

internal val Project.libs
    get() : VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal val Project.librariesLibs
    get() = the<org.gradle.accessors.dm.LibrariesForLibs>()
