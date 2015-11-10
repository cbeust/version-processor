
import com.beust.kobalt.*
import com.beust.kobalt.plugin.application.*
import com.beust.kobalt.plugin.java.*
import com.beust.kobalt.plugin.packaging.*
import com.beust.kobalt.plugin.packaging.*

val processor = javaProject {
    name = "processor"
    version = "0.1"
    directory = "processor"

    assemble {
        jar {
        }
    }
}

val processorExample = javaProject(processor) {
    name = "example"
    group = "com.beust"
    artifactId = name
    version = "0.1"
    directory = "example"

    dependencies {
        compile(file("processor/kobaltBuild/libs/processor-0.1.jar"))
    }

    assemble {
        jar {
        }
    }

    application {
        mainClass = "com.beust.apt.example.Example"
    }
}
