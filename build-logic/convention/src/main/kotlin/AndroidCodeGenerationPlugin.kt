import com.mortitech.blueprint.code.AndroidGenerateCodeTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class AndroidCodeGenerationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Register the custom task
        project.tasks.register<AndroidGenerateCodeTask>("generateCode") {
            // Configure your task here if necessary
        }
    }
}

