# A simple annotation processing example

This project demonstrates a simple annotation processor that is built with [Kobalt](http://beust.com/kobalt).

There are two modules in this project:

- The processor (`processor/`)
- The example using the processor (`example/`)

The `processor` project defines an annotation `@Version` that lets you specify a version number in two ways:

- Either provide a `value` for the version.
- Or you provide a `fileName` pointing to a `Properties` file. This file contains the version number either with the default key `version` or with your own key, which you can specify with the `propertyName` attribute.

For example:

```
@Version(fileName = "example/src/main/properties/version.properties",
    propertyName = "example.version")
public class A {
// ...
```

And the content of the `version.properties` file:

```
example.version=1.4
```

When the annotation processor is run, it generates a source file `GeneratedVersion.java` with a static field containing that version number.

The `example` project has a single class `Example` which is annotated with `@Version` and which then displays the value of the version from the `GeneratedVersion` generated class.

To build and run the example:

```

./kobaltw run

```

