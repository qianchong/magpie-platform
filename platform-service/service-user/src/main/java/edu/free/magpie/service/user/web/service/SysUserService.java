package edu.free.magpie.service.user.web.service;


import edu.free.magpie.api.user.dto.LoginUser;
import edu.free.magpie.api.user.dto.SysPermissionDto;
import edu.free.magpie.api.user.dto.SysRoleDto;
import edu.free.magpie.common.cli.mvc.service.BaseServiceImpl;
import edu.free.magpie.common.supports.util.UtilBeanConvert;
import edu.free.magpie.service.user.web.dao.SysUserDao;
import edu.free.magpie.service.user.web.entity.SysPermissionEntity;
import edu.free.magpie.service.user.web.entity.SysRoleEntity;
import edu.free.magpie.service.user.web.entity.SysUserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysUserService extends BaseServiceImpl<SysUserEntity, String> {
    @Autowired
    private SysUserDao sysUserDao;

    public SysUserService(SysUserDao sysUserDao) {
        super(sysUserDao);
    }

    public LoginUser loadUserByUsername(String loginName) {
        SysUserEntity sysUserEntity = sysUserDao.findByLoginName(loginName);

        LoginUser loginUser = new LoginUser();
        List<SysRoleEntity> sysRoles = sysUserEntity.getRoleList();
        Collection<SysPermissionEntity> permissionAll = new HashSet<>();
        if (!CollectionUtils.isEmpty(sysRoles)) {
            sysRoles.parallelStream().forEach(role -> {
                List<SysPermissionEntity> permissions = role.getPermissions();
                if (!CollectionUtils.isEmpty(permissions)) {
                    permissionAll.addAll(permissions);
                }
            });
        }

        BeanUtils.copyProperties(sysUserEntity, loginUser.getSysUserDto());
        UtilBeanConvert ubc = UtilBeanConvert.getInstance();
        Set<SysRoleDto> roleDtos = ubc.toSet(sysRoles, loginUser.getSysRoleDtos(), SysRoleDto.class);
        Set<SysPermissionDto> permissionDtos = ubc.toSet(permissionAll, loginUser.getSysPermissionDtos(), SysPermissionDto.class);
        loginUser.setSysPermissionDtos(permissionDtos);
        loginUser.setSysRoleDtos(roleDtos);
        return loginUser;
    }
}
