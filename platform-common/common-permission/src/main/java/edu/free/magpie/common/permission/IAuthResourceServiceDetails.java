package edu.free.magpie.common.permission;

import java.util.List;

/**
 * 权限资源接口类
 */
public interface IAuthResourceServiceDetails {
    void saveAuthResourceList(String system, List<? extends IAuthResourceDetails> authResources);

    /**
     * 创建一个要鉴权的资源对象
     *
     * @param systemName
     * @param moduleName
     * @param resourceMethodType
     * @param resourceName
     * @param resourceUrl
     * @param resourceType
     * @param resourceCode
     * @return
     */
    IAuthResourceDetails addAuth(String systemName, int version, String moduleName, String resourceMethodType, String resourceName, String resourceUrl, String resourceType, String resourceCode);
}
