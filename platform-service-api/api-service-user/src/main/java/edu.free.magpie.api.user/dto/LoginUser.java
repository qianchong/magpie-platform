package edu.free.magpie.api.user.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class LoginUser  {
    private SysUserDto sysUserDto = new SysUserDto();
    private Set<SysRoleDto> sysRoleDtos = new HashSet<>();
    private Set<SysPermissionDto> sysPermissionDtos = new HashSet<>();
}
