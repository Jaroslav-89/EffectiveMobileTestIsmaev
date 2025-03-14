pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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
}

rootProject.name = "EffectiveMobileTestIsmaev"
include(":app")
include(":features")
include(":core")
include(":core:database")
include(":core:network")
include(":features:favorite")
include(":features:search")
include(":core:uikit")
include(":features:messages")
include(":features:profile")
include(":features:responses")
include(":core:common")
include(":core:network-utills")
