package edu.free.magpie.common.permission;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilAnnotaion {

    /**
     *
     * @param clsList
     * @param annotation
     * @return
     */
    public static List<Class> getClassByAnnotation(List<Class<?>> clsList, Class annotation) {
        Map<String,Class> classMap = new HashMap<>();
        if (clsList != null && clsList.size() > 0) {
            for (Class<?> cls : clsList) {
                Annotation annotation1 = cls.getAnnotation(annotation);
                if (annotation1 != null) {
                    classMap.put(cls.getSimpleName(),cls);
                    continue;
                }

                //获取类中的所有的方法
                Method[] methods = cls.getDeclaredMethods();
                if (methods != null && methods.length > 0) {
                    for (Method method : methods) {
                        Annotation findAnnotation = method.getAnnotation(annotation);
                        if (findAnnotation != null) {
                            classMap.put(cls.getSimpleName(),cls);
                            continue;
                        }
                    }
                }
            }
        }
        return new ArrayList<>(classMap.values());
    }

    /**
     * 合并指定类上同时包含的Annotation
     *
     * @param clazz 查找的类
     * @return MergeAnnotaion 返回符合条件的nnotation集
     */
    public static <A extends Annotation> MergeAnnotaion mergeAnnotationAndMappingByClass(Class clazz, Class<A> annotation, MergeAnnotaion mergeAnnotaion) {
        Annotation classAuthAnno = AnnotationUtils.findAnnotation(clazz, annotation);
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(clazz, RequestMapping.class);
        mergeAnnotaion.setClassAuthAnnotation(classAuthAnno);
        mergeAnnotaion.setClassMappingAnnotation(requestMapping);
        return mergeAnnotaion;
    }

    /**
     * 返回类上同时具有指定annotation和spring mapping标签的方法，
     *
     * @param clazz 查找的类
     * @return List 返回符合条件的方法集
     */
    public static <A extends Annotation> MergeAnnotaion mergeAnnotationAndMappingByMehtod(Class clazz, Class<A> annotation, MergeAnnotaion mergeAnnotaion) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("roleManage")) {
                System.out.println("aa");
            }
            //目标方法上包含该标签，添加到mergeAnnotaion
            Annotation methodAuthAnno = AnnotationUtils.findAnnotation(method, annotation);
            RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
            if (methodAuthAnno != null) {
                //判断目标方法上是否包含该mapping标签，添加到mergeAnnotaion
                Annotation mappingAnnotation = findMappingAnnotation(method.getAnnotations());
                if (mappingAnnotation != null) {
                    mergeAnnotaion.addMethodAnnos(methodAuthAnno, mappingAnnotation);
                }
            }
        }
        return mergeAnnotaion;
    }

    /**
     * 返回类上同时具有指定annotation和spring mapping标签的方法，
     *
     * @param clazz 查找的类
     * @return List 返回符合条件的方法集
     */
    public static <A extends Annotation> MergeAnnotaion mergeAnnotationAndMapping(Class clazz, Class<A> annotation) {
        MergeAnnotaion mergeAnnotaion = new MergeAnnotaion();
        mergeAnnotaion = mergeAnnotationAndMappingByMehtod(clazz, annotation, mergeAnnotaion);
        if (mergeAnnotaion.getMethodAnnos().isEmpty()) {
            return null;
        }
        mergeAnnotaion = mergeAnnotationAndMappingByClass(clazz, annotation, mergeAnnotaion);
        return mergeAnnotaion;
    }

    /**
     * 查找并返回是否包含spring mapping标签，如果有返回该Mapping Annotation
     *
     * @param annotations
     * @return
     */
    public static Annotation findMappingAnnotation(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> aClass = annotation.annotationType();
            Mapping mapping = AnnotationUtils.findAnnotation(aClass, Mapping.class);
            if (mapping != null) {
                return annotation;
            }
        }
        return null;
    }

    public static String[] getRequestMapping(Class clazz) {
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(clazz, RequestMapping.class);
        if (null != requestMapping) {
            return requestMapping.value();
        }
        return null;
    }

    /**
     * 返回mapping标签上的url
     *
     * @param annotation
     * @return
     */
    public static String[] getMethodUrlsByMappingAnnotation(Annotation annotation) {
        Class<? extends Annotation> aClass = annotation.annotationType();
        Method annoMehthod = ReflectionUtils.findMethod(aClass, "value");
        String[] urls = (String[]) ReflectionUtils.invokeMethod(annoMehthod, annotation);
        return urls;
    }

    /**
     * 返回mapping标签上的方法类型
     *
     * @param mappingAnnotation
     * @return
     */
    public static RequestMethod[] getMethodTypeByMappingAnnotation(Annotation mappingAnnotation) {
        RequestMethod[] methods = null;
        Class<? extends Annotation> aClass = mappingAnnotation.annotationType();
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(aClass, RequestMapping.class);
        Mapping mapping = AnnotationUtils.findAnnotation(aClass, Mapping.class);
        if (requestMapping == null && mapping == null) {  //如果都为空，代表类上没有Request Mapping标签
            return null;
        } else if (requestMapping != null) {            //aClass 是GetMappin PUTMapping等
            methods = requestMapping.method();
        } else if (requestMapping == null) {            //aClass 是requestMapping
            Method annoMehthod = ReflectionUtils.findMethod(aClass, "method");
            methods = (RequestMethod[]) ReflectionUtils.invokeMethod(annoMehthod, mappingAnnotation);
            if (methods.length == 0) {
                methods = new RequestMethod[2];
                methods[0] = RequestMethod.GET;
                methods[1] = RequestMethod.POST;
            }
        }
        return methods;
    }

    /**
     * url如果第一个节母没带/将其添加上
     *
     * @param url
     * @return
     */
    public static String replaceFirstChar(String url) {
        String firstChar = url.substring(0, 1);
        if (!"/".equals(firstChar)) {
            url = url.replaceFirst(firstChar, "/".concat(firstChar));
            return url;
        }
        return url;
    }

}


