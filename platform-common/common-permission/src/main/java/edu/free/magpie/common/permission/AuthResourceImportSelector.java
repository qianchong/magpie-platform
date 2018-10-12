package edu.free.magpie.common.permission;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * project: sneb
 * Description： AuthResource自动装配器
 *
 * @author: ryan
 * @create: Created in 2018/8/14 15:06
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/8/14
 * Version: 0.0.1
 * Modified By:
 */
public class AuthResourceImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{AuthResProcessor.class.getName()};
    }
}
