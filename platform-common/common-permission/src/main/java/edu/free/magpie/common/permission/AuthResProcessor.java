package edu.free.magpie.common.permission;//package cn.com.sneb.platform.open.common.auth;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * project: sneb
 * Description：
 *
 * @author: ryan
 * @create: Created in 2018/8/13 14:45
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/9/20
 * Version: 0.0.1
 * Modified By:
 */
//@Component
public class AuthResProcessor  {

    public final Logger logger = LoggerFactory.getLogger(AuthResProcessor.class);
    @Autowired
    private IAuthResourceServiceDetails iAuthResourceServiceDetails ;

    @Value("${spring.application.name}")
    private String systemName;

    @Value("${eureka.instance.metadata-map.resVersion}")
    private String resVersion = "1";


    public void postProcessAfterInitialization(Class clazz) throws BeansException {
        int version = Integer.valueOf(resVersion);


        MergeAnnotaion mergeAnnotaion = UtilAnnotaion.mergeAnnotationAndMapping(clazz, AuthResource.class);
        if (mergeAnnotaion == null) {
            return ;
        }

        //类上是否有AuthResource,如果有将其转换成SysResourceEntity,存放到AuthResourceContext
        AuthResource classAuth = (AuthResource) mergeAnnotaion.getClassAuthAnnotation();
        if (classAuth != null) {
            IAuthResourceDetails moduleResource = iAuthResourceServiceDetails.addAuth(
                    systemName,version,classAuth.resourceCode(),null,
                    classAuth.resourceName(),null,ResourceTypeEnum.MODULE.name(),classAuth.resourceCode());
            AuthResourceContext.addAuth(moduleResource);
        }
        //获取Controller的mapping地址
        Annotation classMapp = mergeAnnotaion.getClassMappingAnnotation();
        String[] classUrls = UtilAnnotaion.getMethodUrlsByMappingAnnotation(classMapp);

        //检测方法上的authResource标签
        List<MergeAnnotaion.MethodAnno> methodAnnos = mergeAnnotaion.getMethodAnnos();
        for (MergeAnnotaion.MethodAnno methodAnno : methodAnnos) {
            AuthResource auth = (AuthResource) methodAnno.getAuth();
            Annotation mapping = methodAnno.getMapping();
            String resourceName = auth.resourceName();
            String resourceType = auth.resourceType().name();
            String resourceCode = auth.resourceCode();
            String module = auth.module();
            String[] methodurls = UtilAnnotaion.getMethodUrlsByMappingAnnotation(mapping);
            //如果方法上的AuthResource为空，则取类上的resourceCode做为它的模块
            if (StringUtils.isEmpty(module)) {
                if(classAuth == null || StringUtils.isEmpty(classAuth.resourceCode()) ){
                    logger.error("类{}的{}的AuthResource不正确！方法和类上都未检测到module配置信息，建议在方法或类上添module属性",clazz.getSimpleName(),methodurls);
                  // throw new SnebBizException(ResultCommonEnum.CREATE_AUTH_FAIL);
                }
                module = classAuth.resourceCode();
            }

            RequestMethod[] methodTypes = UtilAnnotaion.getMethodTypeByMappingAnnotation(mapping);
            for (String methodUrl : methodurls) {
                String finalUrl = UtilAnnotaion.replaceFirstChar(methodUrl);
                String method = ArrayUtils.toString(methodTypes, ",");
                if (classUrls.length >= 0) {
                    for (String classUrl : classUrls) {
                        finalUrl = UtilAnnotaion.replaceFirstChar(classUrl) + finalUrl;
                        IAuthResourceDetails resourceDetails = iAuthResourceServiceDetails.addAuth(systemName, version, module, method, resourceName, finalUrl, resourceType, resourceCode);
                        AuthResourceContext.addAuth(resourceDetails);
                    }
                } else {
                    IAuthResourceDetails IAuthResourceDetails = iAuthResourceServiceDetails.addAuth(systemName, version, module, method, resourceName, finalUrl, resourceType, resourceCode);
                    AuthResourceContext.addAuth(IAuthResourceDetails);
                }
            }
        }
    }
}
