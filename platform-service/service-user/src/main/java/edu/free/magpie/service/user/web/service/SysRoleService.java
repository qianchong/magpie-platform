package edu.free.magpie.service.user.web.service;

import edu.free.magpie.common.cli.mvc.service.BaseServiceImpl;
import edu.free.magpie.service.user.web.dao.SysRoleDao;
import edu.free.magpie.service.user.web.entity.SysRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService extends BaseServiceImpl<SysRoleEntity, String> {
    @Autowired
    private SysRoleDao sysRoleDao;

    public SysRoleService(SysRoleDao sysRoleDao) {
        super(sysRoleDao);
    }
}
