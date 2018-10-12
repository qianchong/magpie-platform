package edu.free.magpie.common.log.annotaion;

import edu.free.magpie.common.log.importSelector.SnebLoggerImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * project: demo
 * Description： 启用业务日志标签
 *
 * @author: ryan
 * @create: Created in 2018/6/14 10:19
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/14
 * Version: 0.0.1
 * Modified By:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(SnebLoggerImportSelector.class)
public @interface EnableSnebLogger {

}
