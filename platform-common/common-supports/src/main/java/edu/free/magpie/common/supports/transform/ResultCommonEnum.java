package edu.free.magpie.common.supports.transform;

/**
 * project: platform
 * Description： TODO
 *
 * @author: ryan
 * @create: Created in 2018/6/15 10:39
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/15
 * Version: 0.0.1
 * Modified By:
 */
public enum ResultCommonEnum implements IResultEnum {
    /**
     *
     */
    RetCode("", "", ""),
    /**
     * 未知异常
     */
    UNKNOWN("-001", "未知异常", ""),
    /**
     * 操作成功
     */
    SUCCESS("200", "操作成功", ""),
    /**
     * 操作失败
     */
    ERROR("300", "操作失败！", "");

    private String code;
    private String title;
    private String msg;

    ResultCommonEnum(String code, String title, String msg) {
        this.name();
        this.code = code;
        this.title = title;
        this.msg = msg;
        RetCodeProperties.add(this.name(),code,title,msg);
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public IResultEnum get(String key) {
        return RetCodeProperties.get(key);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
