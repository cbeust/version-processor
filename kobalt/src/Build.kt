
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
        jar {
            include(from("service"), to(""), glob("**/*"))
            include(from("kobaltBuild/classes"), to(""), glob("**/*"))
        }
    }

    bintray {
        publish = true
    }
}

// We need to depend on `processor` so we get built after but we need to depend
// on processor.jar, which contains the additional text file defining the annotation processor
val processorExample = project(processor) {
    name = "example"
    group = "com.beust"
    artifactId = name
    version = VERSION
    directory = "example"

    val processorJar = file("processor/kobaltBuild/libs/version-processor-$VERSION.jar")

    dependencies {
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
