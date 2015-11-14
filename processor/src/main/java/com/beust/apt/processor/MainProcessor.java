package com.beust.apt.processor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MainProcessor extends AbstractProcessor {
    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    public MainProcessor() {
        log("Instantiating MainProcessor");
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(Version.class)) {
            Version version = annotatedElement.getAnnotation(Version.class);
            if (annotatedElement.getKind() == ElementKind.CLASS) {
                Element enclosing = annotatedElement.getEnclosingElement();
                if (enclosing.getKind() == ElementKind.PACKAGE) {
                    PackageElement packageElement = (PackageElement) enclosing;
                    String packageName = packageElement.getQualifiedName().toString();
                    JavaFileObject jfo = null;
                    try {
                        log("version = " + version.value());
                        jfo = filer.createSourceFile(packageName + ".GeneratedVersion");
                        BufferedWriter writer = new BufferedWriter(jfo.openWriter());
                        writer.write("package " + packageName + ";\n\n");
                        writer.write("public class GeneratedVersion {\n");
                        writer.write("    public static final String VERSION = \"" + version.value() + "\";\n");
                        writer.write("}\n");
                        writer.close();
                        log("Generated " + jfo.getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    public Set<String> getSupportedAnnotationTypes() {
        Set<String> result = new HashSet<>();
        result.add(Version.class.getCanonicalName());
        return result;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }

    private static void log(String s) {
        System.out.println("[MainProcessor] " + s);
    }
}
