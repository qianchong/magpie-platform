package edu.free.magpie.service.user.web.service;

import edu.free.magpie.common.cli.mvc.service.BaseServiceImpl;
import edu.free.magpie.service.user.web.dao.SysPermissionDao;
import edu.free.magpie.service.user.web.entity.SysPermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysPermissionService extends BaseServiceImpl<SysPermissionEntity, String> {
    @Autowired
    private SysPermissionDao sysPermissionDao;

    public SysPermissionService(SysPermissionDao sysPermissionDao) {
        super(sysPermissionDao);
    }
}
