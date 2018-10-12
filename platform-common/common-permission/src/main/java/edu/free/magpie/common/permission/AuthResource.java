package edu.free.magpie.common.permission;

import org.springframework.beans.factory.annotation.Required;

import java.lang.annotation.*;

/**
 * project: sneb
 * Description： 资源标签，添加标签的资源在系统启动后自动装配到AuthResourceContext中
 *
 * @author: ryan
 * @create: Created in 2018/8/13 13:58
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/8/13
 * Version: 0.0.1
 * Modified By:
 * @see AuthResourceContext
 */
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthResource {
    /**
     * 要加入sneb平台的url的名字
     *
     * @return
     */
    @Required
    String resourceName();

    /**
     * 前台指令码，用于控制前台按钮他链接
     * 前台控件的authCode属性应包含在本数组范围内，才可显示
     */
    @Required
    String resourceCode();

    String[] authCodes() default {};

    /**
     * 资源类型
     */
    ResourceTypeEnum resourceType() default ResourceTypeEnum.RESOURCE;

    /**
     * 所属模块
     * @return
     */
    String module() default "";

    /**
     * 有效范围
     * 1、本系统
     * 2、本模块
     *
     * @return
     */
    String scope() default "";
}
