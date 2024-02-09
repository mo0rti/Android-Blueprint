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
}

rootProject.name = "template"

include(":foundation")
include(":app")
include(":core:domain")
include(":core:data")
include(":core:model")
include(":components:pinpad")
include(":feature:auth-email")
include(":feature:auth-pin")
include(":feature:onboarding")
include(":feature:account")
include(":feature:welcome")
include(":core:testing")
include(":core:database")
include(":core:network")
include(":core:common")
include(":core:navigation")
