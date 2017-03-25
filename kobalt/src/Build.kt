
import com.beust.kobalt.*
import com.beust.kobalt.plugin.application.*
import com.beust.kobalt.plugin.java.*
import com.beust.kobalt.plugin.packaging.*
import com.beust.kobalt.plugin.publish.*
import com.beust.kobalt.plugin.apt.*

val VERSION = "0.2"

val processor = project {
    name = "version-processor"
    group = "com.beust"
    artifactId = name
    version = VERSION
    directory = "processor"

    assemble {
        mavenJars {}
    }

    bintray {
        publish = true
    }
}

val processorExample = project(processor) {
    name = "example"
    group = "com.beust"
    artifactId = name
    version = VERSION
    directory = "example"

    assemble {
        jar {
        }
    }

    application {
        mainClass = "com.beust.version.example.Example"
    }
}
