package edu.free.magpie.common.supports.transform;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * project: platform
 * Description： 数据传输定义的统一格式
 *
 * @author: ryan
 * @create: Created in 2018/6/15 10:41
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/15
 * Version: 0.0.1
 * Modified By:
 */
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 返回码  */
    private String code;

    private String title;

    /** 返回 */
    private String msg;

    /** 返回的具体内容 */
    private T data;

    public ResultVO(IResultEnum iResult) {
        this.code = iResult.getCode();
        this.title = iResult.getTitle();
        this.msg = iResult.getMsg();
    }

    public ResultVO(IResultEnum iResult, T data) {
        this(iResult);
        this.data = data;
    }

    public ResultVO(String code, String title) {
        this(code, title, null);
    }

    public ResultVO(String code, String title, T data) {
        this.code = code;
        this.title = title;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        //默认输出为对象的JSON格式
        String jsonstring = JSON.toJSONString(this);
        return jsonstring;
    }
}
