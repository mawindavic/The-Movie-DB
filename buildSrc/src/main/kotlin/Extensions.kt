import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

fun String.runCommand(
    workingDir: File = File("."),
    timeoutAmount: Long = 60,
    timeoutUnit: TimeUnit = TimeUnit.SECONDS
): String = ProcessBuilder(split("\\s(?=(?:[^'\"`]*(['\"`])[^'\"`]*\\1)*[^'\"`]*$)".toRegex()))
    .directory(workingDir)
    .redirectOutput(ProcessBuilder.Redirect.PIPE)
    .redirectError(ProcessBuilder.Redirect.PIPE)
    .start()
    .apply { waitFor(timeoutAmount, timeoutUnit) }
    .run {
        val error = errorStream.bufferedReader().readText().trim()
        if (error.isNotEmpty()) {
            throw IOException(error)
        }
        inputStream.bufferedReader().readText().trim()
    }

fun DependencyHandler.implementation(name: String) {
    add("implementation", name)
}

fun DependencyHandler.implementation(name: Any) {
    add("implementation", name)
}

fun DependencyHandler.kapt(name: String) {
    add("kapt", name)
}

fun DependencyHandler.compileOnly(name: String) {
    add("compileOnly", name)
}

fun DependencyHandler.api(name: String) {
    add("api", name)
}

fun DependencyHandler.testImplementation(name: String) {
    add("testImplementation", name)
}

fun DependencyHandler.androidTestImplementation(name: String) {
    add("androidTestImplementation", name)
}

fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency =

    uncheckedCast(
        project(
            if (configuration != null) mapOf("path" to path, "configuration" to configuration)
            else mapOf("path" to path)
        )
    )

@Suppress("unchecked_cast")
fun <T> uncheckedCast(obj: Any?): T =
    obj as T