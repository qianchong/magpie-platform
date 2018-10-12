package edu.free.magpie.service.user.web.entity;

import edu.free.magpie.common.cli.mvc.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SysRoleEntity extends BaseEntity {

    @Column(unique = true)
    private String roleCode;

    private String cName;

    /**
     * 角色描述,UI界面显示使用
     */
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<SysUserEntity> userInfos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermissionEntity> permissions;

}

