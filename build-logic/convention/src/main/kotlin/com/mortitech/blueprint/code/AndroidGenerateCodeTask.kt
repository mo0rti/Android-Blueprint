package com.mortitech.blueprint.code

import com.mortitech.blueprint.capitalizeFirstLetter
import com.mortitech.blueprint.pluralize
import com.mortitech.blueprint.runCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class AndroidGenerateCodeTask : DefaultTask() {
    // Define a property for the class name
    @Input
    val classNameToGenerate: String? = project.findProperty("className") as String?

    @TaskAction
    fun generate() {
        println("Generating code for $classNameToGenerate")

        val domainBasePath = "core/domain/src/main/java/com/mortitech/blueprint/domain"
        val modelBasePath = "core/model/src/main/java/com/mortitech/blueprint/model"
        val dataPath = "core/data/src/main/java/com/mortitech/blueprint/data"
        val databasePath = "core/database/src/main/java/com/mortitech/blueprint/database"
        val networkPath = "core/network/src/main/java/com/mortitech/blueprint/network"
        val featureBasePath = "app/src/main/java/com/mortitech/blueprint/template/ui"

        val templatesDir = File("codeTemplates")

        // Mapping of templates to their output directories
        val outputDirs = mapOf(
            "UseCase.txt" to "$domainBasePath/usecase",
            "Entity.txt" to "$databasePath/entity",
            "Dao.txt" to "$databasePath/dao",
            "Repository.txt" to "$dataPath/repository/{{className}}",
            "RepositoryImpl.txt" to "$dataPath/repository/{{className}}",
            "NetworkService.txt" to "$networkPath/service/{{className}}",
            "NetworkServiceLiveImpl.txt" to "$networkPath/service/{{className}}",
            "NetworkServiceMockImpl.txt" to "$networkPath/service/{{className}}",
            "ApiService.txt" to "$networkPath/service/{{className}}",
            "Dto.txt" to "$networkPath/dto",
            "ViewState.txt" to "$featureBasePath/{{className}}",
            "ViewAction.txt" to "$featureBasePath/{{className}}",
            "ViewEvent.txt" to "$featureBasePath/{{className}}",
            "ViewModel.txt" to "$featureBasePath/{{className}}"
        )

        val domainModelBaseDir = File(modelBasePath)
        val domainModelFiles = getDomainModelFiles(classNameToGenerate, domainModelBaseDir)

        domainModelFiles.forEach { domainFile ->
            val className = domainFile.nameWithoutExtension
            val fieldInfos = extractFieldInfo(domainFile)

            if (shouldGenerateCode(classNameToGenerate, domainFile)) {
                templatesDir.listFiles()?.forEach { templateFile ->
                    val content = generateContent(templateFile, className, fieldInfos)
                    writeToFile(templateFile, className, content, outputDirs)
                }
            }
        }
    }
}

// Function to extract field information from a Kotlin file
fun extractFieldInfo(file: File): List<FieldInfo> {
    return file.readLines()
        .filter { it.trim().startsWith("val") }
        .mapNotNull { line ->
            line.trim().removeSuffix(",").split(":").takeIf { it.size == 2 }?.let { segments ->
                FieldInfo(
                    name = segments[0].substringAfter("val").trim(),
                    type = segments[1].trim()
                )
            }
        }
}

// Function to generate DTO fields content
fun generateDtoFieldsContent(fieldInfos: List<FieldInfo>): String {
    return fieldInfos.joinToString(",\n    ") { fieldInfo ->
        "@SerializedName(\"${fieldInfo.name}\")\n    val ${fieldInfo.name}: ${fieldInfo.type}"
    }
}

// Function to generate DTO fields map content
fun generateMapDtoFieldsToDomainFieldsContent(fieldInfos: List<FieldInfo>): String {
    return fieldInfos.joinToString(",\n            ") { fieldInfo ->
        "${fieldInfo.name} = this.${fieldInfo.name}"
    }
}

// Function to generate DTO fields map content
fun generateMapDomainFieldsToDtoFieldsContent(fieldInfos: List<FieldInfo>): String {
    return fieldInfos.joinToString(",\n                ") { fieldInfo ->
        "${fieldInfo.name} = model.${fieldInfo.name}"
    }
}

// Function to generate ViewState fields content
fun generateViewStateFieldsContent(fieldInfos: List<FieldInfo>): String {
    return fieldInfos.joinToString(",\n    ") { fieldInfo ->
        val defaultValue = getDefaultFieldValue(fieldInfo.type)
        "val ${fieldInfo.name}: ${fieldInfo.type} = $defaultValue"
    }
}

// Function to get the default value for a given type
fun getDefaultFieldValue(type: String): String {
    return when (type) {
        "String" -> "\"\""
        "Int" -> "0"
        "Boolean" -> "false"
        "Float" -> "0.0f"
        "Double" -> "0.0"
        // Add more types and their default values
        else -> "null" // This is a placeholder, you might want a more sophisticated default handling
    }
}

// Function to generate ViewModel field cases content
fun generateViewModelFieldCases(className: String, fieldInfos: List<FieldInfo>): String {
    return fieldInfos.joinToString("\n                    ") { fieldInfo ->
        val caseValue = getCaseValue(fieldInfo.type)
        "${className.capitalizeFirstLetter()}FieldType.${fieldInfo.name.capitalizeFirstLetter()} -> updateViewState { copy(${fieldInfo.name} = $caseValue) }"
    }
}

// Function to get the case value for a given type
fun getCaseValue(type: String): String {
    return when (type) {
        "String" -> "action.value.toString()"
        "Int" -> "action.value.toString().toInt()"
        "Double" -> "action.value.toString().toDouble()"
        "Float" -> "action.value.toString().toFloat()"
        // Add more type conversions if needed
        else -> "action.value as $type"
    }
}

// Function to generate ViewAction enum fields content
fun generateViewActionEnumFields(fieldInfos: List<FieldInfo>): String {
    return fieldInfos.joinToString(",\n    ") { fieldInfo ->
        fieldInfo.name.capitalizeFirstLetter()
    }
}

fun generateViewModelPropertyAssignments(fieldInfos: List<FieldInfo>): String {
    return fieldInfos.joinToString(",\n                    ") { fieldInfo ->
        "${fieldInfo.name} = viewState.value.${fieldInfo.name}"
    }
}

// Main function that uses the smaller functions to replace placeholders in the template
fun generateContent(template: File, className: String, fieldInfos: List<FieldInfo>): String {
    var content = template.readText()

    // Replace class name placeholders
    content = replaceClassNamePlaceholders(content, className)

    when {
        template.name.contains("Dto") -> {
            content = content.replace("{{DtoFields}}", generateDtoFieldsContent(fieldInfos))
            content = content.replace("{{MapDtoFieldsToDomainFields}}", generateMapDtoFieldsToDomainFieldsContent(fieldInfos))
            content = content.replace("{{MapDomainFieldsToDtoFields}}", generateMapDomainFieldsToDtoFieldsContent(fieldInfos))
        }
        template.nameWithoutExtension.contains("ViewState") -> {
            content = content.replace("{{ViewStateFields}}", generateViewStateFieldsContent(fieldInfos))
        }
        template.nameWithoutExtension.contains("ViewAction") -> {
            content = content.replace("{{EnumFields}}", generateViewActionEnumFields(fieldInfos))
        }
        template.name.contains("ViewModel") -> {
            content = content.replace("{{FieldCases}}", generateViewModelFieldCases(className, fieldInfos))
            content = content.replace("{{PropertyAssignments}}", generateViewModelPropertyAssignments(fieldInfos))
        }
        // Add additional template types if necessary.
    }

    return content
}

// Function to replace class name placeholders
fun replaceClassNamePlaceholders(content: String, className: String): String {
    return content.replace("{{ClassNamePlural}}", className.pluralize())
        .replace("{{ClassName}}", className)
        .replace("{{className}}", className.lowercase())
}


fun getDomainModelFiles(classNameToGenerate: String?, baseDir: File): List<File> {
    return if (classNameToGenerate != null) {
        listOf(File("$baseDir/$classNameToGenerate.kt")).filter { it.exists() }
    } else {
        baseDir.walkTopDown().filter { it.isFile && it.extension == "kt" }.toList()
    }
}

fun shouldGenerateCode(classNameToGenerate: String?, domainFile: File): Boolean {
    return classNameToGenerate != null || domainFile.readText().contains("@GenerateCode")
}

fun writeToFile(templateFile: File, className: String, content: String, outputDirs: Map<String, String>) {
    val outputDirPath = outputDirs[templateFile.name]?.replace("{{className}}", className.lowercase())
        ?: throw IllegalStateException("No output directory defined for template: ${templateFile.name}")
    val outputDir = File(outputDirPath)
    outputDir.mkdirs()

    val templateFileName = "${className}${templateFile.nameWithoutExtension}"
    val outputFilePath = outputDir.resolve("$templateFileName.kt")
    outputFilePath.writeText(content)

    // Add the file to Git
    "git add $outputFilePath".runCommand()

    println("Generated $templateFileName at $outputFilePath")
}