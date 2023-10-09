package com.example.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply(librariesLibs.plugins.android.library)
                apply(librariesLibs.plugins.kotlin.android)
            }
        }
    }
}