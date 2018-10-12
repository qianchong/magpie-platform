package edu.free.magpie.api.user.dto;

import lombok.Data;

@Data
public class SysUserDto {
    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    /**
     *  密码
     */
    private String password;

    /**
     * 用户状态,0:创建未认证 1:正常状态 2：用户被锁定.
     */
    private byte state;
}
