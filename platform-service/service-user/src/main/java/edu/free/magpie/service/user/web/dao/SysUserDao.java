package edu.free.magpie.service.user.web.dao;

import edu.free.magpie.common.cli.mvc.dao.BaseDao;
import edu.free.magpie.service.user.web.entity.SysUserEntity;

public interface SysUserDao extends BaseDao<SysUserEntity, String> {
    SysUserEntity findByLoginName(String loginName);
}
