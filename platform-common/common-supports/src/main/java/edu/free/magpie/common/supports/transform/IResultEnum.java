package edu.free.magpie.common.supports.transform;

/**
 * project: sneb
 * Description： TODO
 *
 * @author: ryan
 * @create: Created in 2018/6/15 10:37
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/7
 * Version: 0.0.1
 * Modified By:
 */
public interface IResultEnum {
    String getCode();
    String getTitle();
    String getMsg();
    IResultEnum get(String key);
}
