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
        create("appPlugins") {
            from(files("gradle/appPlugins.versions.toml"))
        }
    }
}

rootProject.name = "IsEvenApp"
include(":app")
include(":feature:keyboard-input")
include(":core:data")
include(":core:network")
