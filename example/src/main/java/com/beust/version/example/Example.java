package com.beust.version.example;

import com.beust.version.Version;

@Version(fileName = "example/src/main/properties/version.properties", propertyName = "example.version")
class Example {
    public static void main(String[] argv) throws Exception {
        // Set this to true and launch this class if you want to debug the annotation processor.
        boolean debug = true;
        if (debug) {
            String root = "/c/Users/cbeust/java/version-processor/";
            String[] args = new String[]{
                    "-classpath",
                    "processor/kobaltBuild/libs/version-processor-0.2.jar",
                    "-processor", "com.beust.version.VersionProcessor",
                    "example/src/main/java/com/beust/version/example/Example.java"
            };
            // You need $JDK_HOME/lib/tools.jar for this
//            System.out.println("Launching in " + new File(".").getAbsolutePath() + "\n  " + Arrays.toString(args));
//            com.sun.tools.javac.Main.main(args);
        }

        System.out.println("Version generated: " + GeneratedVersion.VERSION);
    }
}