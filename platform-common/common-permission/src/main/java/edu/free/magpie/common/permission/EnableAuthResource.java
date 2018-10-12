package edu.free.magpie.common.permission;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * project: sneb
 * Description： TODO
 *
 * @author: ryan
 * @create: Created in 2018/8/14 15:08
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/8/14
 * Version: 0.0.1
 * Modified By:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(AuthResourceImportSelector.class)
public @interface EnableAuthResource {
}
