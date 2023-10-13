dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }

        create("conventionPlugins") {
            from(files("../gradle/conventionPlugins.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"
include("convention")