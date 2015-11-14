package com.beust.apt.example;

import com.beust.apt.processor.Version;

import java.io.File;

@Version(fileName = "example/src/main/properties/version.properties", propertyName = "example.version")
class Example {
    public Example() {
        System.out.println("Instantiating Example");
    }

    public static void main(String[] argv) throws Exception {
        String root = "/Users/cbeust/java/java-apt-example/";
        String[] args = new String[] {
                "-classpath",
                root + "processor/kobaltBuild/libs/processor-0.1.jar",
                "-processor", "com.beust.apt.processor.VersionProcessor",
                root + "example/src/main/java/com/beust/apt/example/Example.java"
        };
        // Uncomment this line and launch this class to debug the annotation processor.
        // You need $JDK_HOME/lib/tools.jar for this
//        com.sun.tools.javac.Main.main(args);

        File file = new File(".");
        System.out.println("Version generated: " + GeneratedVersion.VERSION);
//        new Example();
    }
}