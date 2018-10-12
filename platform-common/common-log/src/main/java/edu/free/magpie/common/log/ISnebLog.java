package edu.free.magpie.common.log;

import com.alibaba.fastjson.JSONObject;

/**
 * project: platform
 * Description： sneb-log平台日志接口类，日志使用系统可以实现该接口提供当前用户信息，以额外的其他信息，
 *
 * @author: ryan
 * @create: Created in 2018/6/14 11:30
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/14
 * Version: 0.0.1
 * Modified By:
 */
public interface ISnebLog {

    /**
     * 获取操作人
     *
     * @return
     */
    String operator();


    /**
     * 其他信息
     *
     * @return
     */
    JSONObject otherInfo();
}
