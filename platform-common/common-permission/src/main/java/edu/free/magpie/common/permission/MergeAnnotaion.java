package edu.free.magpie.common.permission;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class MergeAnnotaion {
    private Annotation classAuthAnnotation;
    private Annotation classMappingAnnotation;
    private List<MethodAnno> methodAnnos = new ArrayList<>();

    public MergeAnnotaion() {
    }

    public class MethodAnno {
        private Annotation mapping;
        private Annotation auth;

        public MethodAnno(Annotation auth, Annotation mapping) {
            this.mapping = mapping;
            this.auth = auth;
        }

        public Annotation getMapping() {
            return mapping;
        }

        public void setMapping(Annotation mapping) {
            this.mapping = mapping;
        }

        public Annotation getAuth() {
            return auth;
        }

        public void setAuth(Annotation auth) {
            this.auth = auth;
        }
    }

    public Annotation getClassAuthAnnotation() {
        return classAuthAnnotation;
    }

    public void setClassAuthAnnotation(Annotation classAuthAnnotation) {
        this.classAuthAnnotation = classAuthAnnotation;
    }

    public Annotation getClassMappingAnnotation() {
        return classMappingAnnotation;
    }

    public void setClassMappingAnnotation(Annotation classMappingAnnotation) {
        this.classMappingAnnotation = classMappingAnnotation;
    }

    public List<MethodAnno> getMethodAnnos() {
        return methodAnnos;
    }

    public void addMethodAnnos(Annotation auth, Annotation mapping) {
        methodAnnos.add(new MethodAnno(auth, mapping));
    }
}