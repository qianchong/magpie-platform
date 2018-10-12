package edu.free.magpie.common.permission;

import java.util.ArrayList;
import java.util.List;

/**
 * project: sneb
 * Description： 资源上下文，在系统启动时自载装载AuthResourceDetails
 *
 * @see IAuthResourceDetails
 * @author: ryan
 * @create: Created in 2018/9/19 13:58
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/8/13
 * Version: 0.0.1
 * Modified By:
 */
public class AuthResourceContext {
    private static List<IAuthResourceDetails> authResourceDetailsList = new ArrayList<>();

    /**
     * 添加权限资源
     */
    public static void addAuth(IAuthResourceDetails iAuthResourceDetails) {
        authResourceDetailsList.add(iAuthResourceDetails);
    }

    public static List<IAuthResourceDetails> getAuthResourceDetailsList() {
        return authResourceDetailsList;
    }
}
