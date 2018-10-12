package edu.free.magpie.service.user.web.entity;

import edu.free.magpie.common.cli.mvc.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SysPermissionEntity extends BaseEntity {

    @Column(columnDefinition = "enum('menu','resource')")
    private String resourceType;

    // 资源路径.
    private String url;

    /**
     * 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    private String permission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private SysPermissionEntity parentPermission;

    @OneToMany(targetEntity = SysPermissionEntity.class, cascade = {CascadeType.ALL}, mappedBy = "parentPermission")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("orderNum")
    private List<SysPermissionEntity> childPermission = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "permissionId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRoleEntity> roles;

    /**
     * 资源是否可用
     */
    private Boolean available = Boolean.FALSE;

    /**
     * 资源图标
     */
    @Column(columnDefinition = "varchar(128) default 'fa fa-bars'")
    private String icon;

    private Integer orderNum;
}
