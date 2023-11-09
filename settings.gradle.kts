pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("conventionPlugins") {
            from(files("gradle/conventionPlugins.versions.toml"))
        }
    }
}

rootProject.name = "IsEvenApp"
include(":app")
include(":feature:keyboard-input")
include(":core:data")
include(":core:network")
include(":core:model")
include(":core:domain")
include(":feature:slider-input")
include(":feature:numberList")
include(":feature:draw-input")
include(":core:digit-classifier")
