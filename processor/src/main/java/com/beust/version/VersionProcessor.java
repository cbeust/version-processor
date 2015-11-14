package com.beust.version;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class VersionProcessor extends AbstractProcessor {
    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

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
                        String versionValue = findVersionValue(version);
                        if (versionValue != null) {
                            log("Version value found: " + versionValue);
                            jfo = filer.createSourceFile(packageName + ".GeneratedVersion");
                            BufferedWriter writer = new BufferedWriter(jfo.openWriter());
                            writer.write("package " + packageName + ";\n\n");
                            writer.write("public class GeneratedVersion {\n");
                            writer.write("    public static final String VERSION = \"" + versionValue + "\";\n");
                            writer.write("}\n");
                            writer.close();
                            log("Generated " + jfo.getName());
                        } else {
                            error("Need to specify either @Version(value) or @Version(fileName)");
                        }
                    } catch (IOException e) {
                        error("IOException while running", e);
                    }
                }
            }
        }
        return true;
    }

    private boolean isDefault(String s) {
        return s.length() == 0;
    }

    private String findVersionValue(Version version) throws IOException {
        if (! isDefault(version.value())) {
            return version.value();
        } else if (! isDefault(version.fileName())) {
            File f = new File(version.fileName());
            if (f.exists()) {
                log("Found " + f);
                Properties p = new Properties();
                p.load(new FileReader(f));
                String result = p.getProperty(version.propertyName());
                return result;
            } else {
                error("Couldn't find " + version.fileName());
            }
        }

        return null;
    }

    public Set<String> getSupportedAnnotationTypes() {
        Set<String> result = new HashSet<>();
        result.add(Version.class.getCanonicalName());
        return result;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    private void log(String s) {
        messager.printMessage(Diagnostic.Kind.OTHER, "[VersionProcessor] " + s);
//        System.out.println("[VersionProcessor] " + s);
    }

    private void error(String s) {
        error(s, null);
    }

    private void error(String s, Throwable t) {
        messager.printMessage(Diagnostic.Kind.ERROR, s + " " + (t != null ? t.getMessage() : ""));
    }
}
