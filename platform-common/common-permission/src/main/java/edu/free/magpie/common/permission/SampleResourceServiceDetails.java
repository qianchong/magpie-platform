package edu.free.magpie.common.permission;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限资源接口类
 */
@Component
public class SampleResourceServiceDetails implements IAuthResourceServiceDetails {

    @Override
    public void saveAuthResourceList(String system, List<? extends IAuthResourceDetails> authResources) {

    }

    @Override
    public IAuthResourceDetails addAuth(String systemName, int version, String moduleName, String resourceMethodType, String resourceName, String resourceUrl, String resourceType, String resourceCode) {
        SampleAuthResourceDetails sysResourceEntity = new SampleAuthResourceDetails();
        sysResourceEntity.setModuleName(moduleName);
        sysResourceEntity.setMethod(resourceMethodType);
        sysResourceEntity.setName(resourceName);
        sysResourceEntity.setUrl(resourceUrl);
        sysResourceEntity.setType(resourceType);
        sysResourceEntity.setCode(resourceCode);
        sysResourceEntity.setSystemName(systemName);
        sysResourceEntity.setVersion(version);
        return sysResourceEntity;
    }
}
