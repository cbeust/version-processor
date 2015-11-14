
import com.beust.kobalt.*
import com.beust.kobalt.plugin.application.*
import com.beust.kobalt.plugin.java.*
import com.beust.kobalt.plugin.packaging.*
import com.beust.kobalt.plugin.publish.*
import com.beust.kobalt.plugin.apt.*

val processor = javaProject {
    name = "version-processor"
    group = "com.beust"
    artifactId = name
    version = "0.1"
    directory = "processor"

    assemble {
        mavenJars {}
    }

    jcenter {
        publish = true
    }
}

val processorExample = javaProject(processor) {
    name = "example"
    group = "com.beust"
    artifactId = name
    version = "0.1"
    directory = "example"

    val processorJar = "processor/kobaltBuild/libs/version-processor-0.1.jar"

    dependencies {
        apt(file(processorJar))
        compile(file(processorJar))
    }

    assemble {
        jar {
        }
    }

    application {
        mainClass = "com.beust.apt.example.Example"
    }
}
