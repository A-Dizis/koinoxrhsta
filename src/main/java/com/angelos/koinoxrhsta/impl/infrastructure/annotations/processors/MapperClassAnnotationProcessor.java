package com.angelos.koinoxrhsta.impl.infrastructure.annotations.processors;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import org.springframework.javapoet.ClassName;
import org.springframework.javapoet.CodeBlock;
import org.springframework.javapoet.CodeBlock.Builder;
import org.springframework.javapoet.FieldSpec;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.MethodSpec;
import org.springframework.javapoet.ParameterizedTypeName;
import org.springframework.javapoet.TypeName;
import org.springframework.javapoet.TypeSpec;
import org.springframework.javapoet.WildcardTypeName;

import com.angelos.koinoxrhsta.impl.infrastructure.annotations.MapperClass;
import com.google.auto.service.AutoService;

@AutoService(Processor.class)
public class MapperClassAnnotationProcessor extends AbstractProcessor {

    private Filer filer;
    private Messager messager;

    public MapperClassAnnotationProcessor() {

    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>();
        annotataions.add(MapperClass.class.getCanonicalName());
        return annotataions;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        File path = new File("src/main/java");
        System.out.println(path);
        Map<String, String> elemsMap = new HashMap<>();

        String mapper = "";
        String entity = "";

        Map<String,String> mappers = new HashMap<>();

        for (Element elem : roundEnv.getElementsAnnotatedWith(MapperClass.class)) {
            String key = "";
            String value = "";
        }

        // Map<String, String> elemsMap = new HashMap<>();
        
        // for (Element elem : roundEnv.getElementsAnnotatedWith(MapperClass.class)) {
        //     elemsMap.put(null, null) elem.getAnnotationMirrors().get(0).getElementValues().entrySet()
        //                                                         .stream().collect(Collectors.toMap(u->u.getKey().getSimpleName().toString(), u->u.getValue().getValue().toString()));
        //     String message = "annotation found in " + elem.getSimpleName() + " with complexity " + elemsMap.get("entityClass");
        //     processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, message);    

    

            // try {
            //     makeClassSource(elemsMap).writeTo(path);
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
        // }
        return true; // no further processing of this annotation type
    }

    private JavaFile makeClassSource(Map<String, String> elemsMap) throws IOException {
        TypeName wildcard = WildcardTypeName.subtypeOf(Object.class);
        TypeName classOfAny = ParameterizedTypeName.get(ClassName.get(Class.class), wildcard);
        TypeName mapOfStringAndClassOfAny = ParameterizedTypeName.get(ClassName.get(Map.class), classOfAny, classOfAny);
        TypeName hashMapOfStringAndClassOfAny = ParameterizedTypeName.get(ClassName.get(HashMap.class), classOfAny, classOfAny);

        FieldSpec map = FieldSpec.builder(mapOfStringAndClassOfAny, "EntityKeyMap")
        .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
        .initializer("new $T()", hashMapOfStringAndClassOfAny)
        .build();

        MethodSpec constructor = MethodSpec.methodBuilder("MapperConfiguration")
                .addModifiers(Modifier.PRIVATE)
                .build();

        Builder staticBlockBuilder = CodeBlock.builder();
        for (Entry<String,String> elem : elemsMap.entrySet()) {
            staticBlockBuilder.add("EntityKeyMap.put("+ elem.getKey() +"," + elem.getValue() +  ")");
        }

        TypeSpec MapperConfigurationClass = TypeSpec.classBuilder("MapperConfiguration")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(map)
                .addMethod(constructor)
                .addStaticBlock(staticBlockBuilder.build())
                .build();

        JavaFile javaFile = JavaFile.builder("com.angelos.koinoxrhsta.produced.impl.infrastructure", MapperConfigurationClass)
                .build();

        System.out.println(javaFile.toString());

        return javaFile;
    }

    private void error(Element e, String msg, Object... args) {
    messager.printMessage(
    	Diagnostic.Kind.ERROR,
    	String.format(msg, args),
    	e);
  }

}
