package edu.free.magpie.common.log.annotaion;

import edu.free.magpie.common.log.importSelector.SnebLoggerImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * project: sneb
 * Description： 日志业务注解
 *
 * @author: ryan
 * @create: Created in 2018/6/14 11:19
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/7
 * Version: 0.0.1
 * Modified By:
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Import(SnebLoggerImportSelector.class)
@Documented
public @interface SnebLogger {
    String value() default "";
}
