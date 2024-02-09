package com.mortitech.blueprint

// Utility function to run a command in the shell
fun String.runCommand(): String? {
    return try {
        val parts = this.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor()
        proc.inputStream.bufferedReader().readText()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

// Utility function to pluralize strings properly
fun String.pluralize(): String {
    return when {
        endsWith("y") -> dropLast(1) + "ies"
        endsWith("s") -> this + "es"
        endsWith("ch") -> this + "es"
        endsWith("x") -> this + "es"
        endsWith("sh") -> this + "es"
        else -> this + "s"
    }
}

// Extension function to capitalize the first letter of a string
fun String.capitalizeFirstLetter(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}