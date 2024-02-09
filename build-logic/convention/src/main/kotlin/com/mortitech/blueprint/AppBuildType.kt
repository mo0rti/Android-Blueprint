package com.mortitech.blueprint

/**
 * This is shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class AppBuildType(val applicationIdSuffix: String? = null) {
    DEV(".dev"),
    RELEASE,
    ACCEPTANCE(".acceptance"),
    BENCHMARK(".benchmark")
}
