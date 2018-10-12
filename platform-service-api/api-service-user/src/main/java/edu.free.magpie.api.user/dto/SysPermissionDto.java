package edu.free.magpie.api.user.dto;

import lombok.Data;

@Data
public class SysPermissionDto {

    private String resourceType;
    /**
     * 资源路径.
     */
    private String url;

    /**
     * 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    private String permission;

}
