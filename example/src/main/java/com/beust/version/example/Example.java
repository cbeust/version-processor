package com.beust.version.example;

import com.beust.version.Version;

@Version(fileName = "example/src/main/properties/version.properties", propertyName = "example.version")
class Example {
    public static void main(String[] argv) throws Exception {
        // Set this to true and launch this class if you want to debug the annotation processor.
        boolean debug = false;
        if (debug) {
            String root = "/Users/cbeust/java/java-apt-example/";
            String[] args = new String[]{
                    "-classpath",
                    root + "processor/kobaltBuild/libs/processor-0.1.jar",
                    "-processor", "com.beust.version.VersionProcessor",
                    root + "example/src/main/java/com/beust/apt/example/Example.java"
            };
            // You need $JDK_HOME/lib/tools.jar for this
//            com.sun.tools.javac.Main.main(args);
        }

        System.out.println("Version generated: " + GeneratedVersion.VERSION);
    }
}