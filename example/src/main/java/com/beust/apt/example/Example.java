package com.beust.apt.example;

import com.beust.apt.processor.Version;

import java.io.File;

@Version("1.3")
class Example {
    public Example() {
        System.out.println("Instantiating Example");
    }

    public static void main(String[] argv) throws Exception {
        // Uncomment these lines and launch this class to debug the annotation processor.
//        String[] args = new String[] {
////                "-proc:only",
//                "-classpath", "/Users/beust/home/java/java-apt-example/processor/kobaltBuild/libs/processor-0.1.jar",
//                "-processor", "com.beust.apt.processor.MainProcessor",
//                "/Users/beust/java/java-apt-example/example/src/main/java/com/beust/apt/example/Example.java"
//        };
//        com.sun.tools.javac.Main.main(args);

        File file = new File(".");
        System.out.println("Version generated: " + GeneratedVersion.VERSION);
        new Example();
    }
}