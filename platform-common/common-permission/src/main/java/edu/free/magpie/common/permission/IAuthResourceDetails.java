package edu.free.magpie.common.permission;

/**
 * project: sneb
 * Description： 权限资源接口类
 * @author: ryan
 * @create: Created in 2018/9/19 13:58
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/8/13
 * Version: 0.0.1
 * Modified By:
 */
public interface IAuthResourceDetails {
    /**
     * 资源ID
     *
     * @return
     */
    Long getId();

    /**
     * 父资源
     *
     * @return
     */
    Long getPid();

    /**
     * 资源名称
     *
     * @return
     */
    String getName();

    /**
     * 资源编号
     *
     * @return
     */
    String getCode();

    /**
     * 资源类型
     */
    String getType();

    /**
     * 资源排序
     *
     * @return
     */
    Long getOrderNum();

    /**
     * 资源图标
     *
     * @return
     */
    String getIcon();

    String getUrl();

    String getMethod();

    String getSystemName();

    String getModuleName();

    Integer getVersion();
}
