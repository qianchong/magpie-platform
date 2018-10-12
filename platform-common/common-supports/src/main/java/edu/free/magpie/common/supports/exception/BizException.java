package edu.free.magpie.common.supports.exception;


import edu.free.magpie.common.supports.transform.IResultEnum;

/**
 * project: platform
 * Description： TODO
 *
 * @author: ryan
 * @create: Created in 2018/6/15 11:39
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/15
 * Version: 0.0.1
 * Modified By:
 */
public class BizException extends RuntimeException {
    private IResultEnum iResult;
    private Object data;

    public BizException(String errorCode, String errorTitle, String errorMsg){
        iResult = new IResultEnum() {
            @Override
            public String getCode() {
                return errorCode;
            }

            @Override
            public String getTitle() {
                return errorTitle;
            }

            @Override
            public String getMsg() {
                return errorMsg;
            }

            @Override
            public IResultEnum get(String key) {
                return null;
            }
        };
        //this(iResult);
    }

    public BizException(IResultEnum iResult) {
        super(iResult.getMsg());
        this.iResult = iResult;
    }

    public BizException(IResultEnum iResult, Object data) {
        super(iResult.getMsg());
        this.iResult = iResult;
        this.data = data;
    }

    public IResultEnum getErrorResult() {
        return iResult;
    }
}
