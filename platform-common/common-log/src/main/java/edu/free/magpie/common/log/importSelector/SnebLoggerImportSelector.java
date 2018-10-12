package edu.free.magpie.common.log.importSelector;

import edu.free.magpie.common.log.SimpleSnebLogConfig;
import edu.free.magpie.common.log.advice.SenbLoggerAspect;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * project: demo
 *
 * @author: ryan
 * @create: Created in 2018/6/14 10:15
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/14
 * Version: 0.0.1
 * Modified By:
 */
public class SnebLoggerImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{SenbLoggerAspect.class.getName(), SimpleSnebLogConfig.class.getName()};
    }
}
