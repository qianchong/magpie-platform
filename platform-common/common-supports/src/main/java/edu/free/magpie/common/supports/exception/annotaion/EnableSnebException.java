package edu.free.magpie.common.supports.exception.annotaion;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * project: sneb
 * Description： TODO
 *
 * @author: ryan
 * @create: Created in 2018/6/22 11:40
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/22
 * Version: 0.0.1
 * Modified By:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(SnebExceptionImportSelector.class)
public @interface EnableSnebException {
}
