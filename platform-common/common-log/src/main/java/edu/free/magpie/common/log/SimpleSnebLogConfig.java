package edu.free.magpie.common.log;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * project: sneb
 * Description： TODO
 *
 * @author: ryan
 * @create: Created in 2018/6/26 14:16
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/26
 * Version: 0.0.1
 * Modified By:
 */
@Component
public class SimpleSnebLogConfig implements ISnebLog {

    @Override
    public String operator() {
        return "无";
    }

    @Override
    public JSONObject otherInfo() {
        JSONObject otherInfo = new JSONObject();
        otherInfo.put("app", "考试系统");
        otherInfo.put("desc", "例子项目");
        return otherInfo;
    }
}
