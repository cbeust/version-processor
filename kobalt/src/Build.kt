
import com.beust.kobalt.*
import com.beust.kobalt.plugin.application.*
import com.beust.kobalt.plugin.java.*
import com.beust.kobalt.plugin.packaging.*
import com.beust.kobalt.plugin.publish.*
import com.beust.kobalt.plugin.apt.*

val processor = project {
    name = "version-processor"
    group = "com.beust"
    artifactId = name
    version = "0.2"
    directory = "processor"

    assemble {
        mavenJars {}
    }

    bintray {
        publish = true
    }
}

val processorExample = javaProject(processor) {
    name = "example"
    group = "com.beust"
    artifactId = name
    version = "0.2"
    directory = "example"

    val processorJar =
            "com.beust:version-processor:0.1"
//            file("processor/kobaltBuild/libs/version-processor-0.1.jar")

    dependencies {
        apt(processorJar)
        compile(processorJar)
    }

    assemble {
        jar {
        }
    }

    application {
        mainClass = "com.beust.version.example.Example"
    }
}
