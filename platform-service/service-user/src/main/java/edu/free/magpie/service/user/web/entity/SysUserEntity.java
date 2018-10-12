package edu.free.magpie.service.user.web.entity;

import com.fasterxml.jackson.annotation.JsonView;
import edu.free.magpie.common.cli.mvc.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SysUserEntity extends BaseEntity {

    public interface UserSimpleView {
    }

    @JsonView(UserSimpleView.class)
    @Column(unique = true, columnDefinition = "varchar(100) COMMENT '账号'")
    private String loginName;

    /**
     * 名称（昵称或者真实姓名，不同系统不同定义）
     */
    private String userName;

    private String password;

    /**
     * 用户状态,0:创建未认证 1:正常状态 2：用户被锁定.
     */
    private byte state;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "oid")},
            inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "oid")})
    private List<SysRoleEntity> roleList;

}
