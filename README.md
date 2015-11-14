# A simple annotation processing example

This project demonstrates a simple annotation processor that is built with [Kobalt](http://beust.com/kobalt).

There are two modules in this project:

- The processor (`processor/`)
- The example using the processor (`example/`)

The `processor` project defines an annotation `Version` that lets you specify a version number. When the annotation processor is run, it generates a source file `GeneratedVersion.java` with a static field contains that version number.

The `example` project has a single class `Example` which is annotated with `@Version` and which then displays the value of the version from the `GeneratedVersion` generated class.

Build with `./kobaltw run`.
