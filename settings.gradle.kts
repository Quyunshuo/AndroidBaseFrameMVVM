pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = java.net.URI.create("https://maven.google.com") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = java.net.URI.create("https://maven.google.com") }
    }
}

rootProject.name = "AndroidBaseFrameMVVM"

include(
    ":app",
    ":lib_base",
    ":lib_common",
    ":module_home"
)