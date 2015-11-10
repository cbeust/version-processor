package com.beust.apt.example;

import com.beust.apt.processor.Version;

@Version("1.2")
class Example {
    public Example() {
        System.out.println("Instantiating Example");
    }

    public static void main(String[] argv) {
        new Example();
    }

}