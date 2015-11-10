package com.beust.apt.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.HashSet;
import java.util.Set;

public class MainProcessor extends AbstractProcessor {
    public MainProcessor() {
        System.out.println("Instantiating MainProcessor");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("Processing annotations $annotations");
        return true;
    }

    public Set<String> getSupportedAnnotationTypes() {
        Set<String> result = new HashSet<>();
        result.add("Version");
        return result;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }
}
