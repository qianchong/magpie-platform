package edu.free.magpie.common.supports.transform;


import java.io.Serializable;

/**
 * project: platform
 * Description： TODO
 *
 * @author: ryan
 * @create: Created in 2018/6/15 10:44
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/15
 * Version: 0.0.1
 * Modified By:
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回成功,不传入IResult
     * 默认成功使用ResultStatuEnum.SUCCESS
     * code: 200
     * msg: 操作成功
     *
     * @return
     */
    public static ResultVO success() {
        return success(ResultCommonEnum.SUCCESS);
    }

    /**
     * 返回成功,不传入IResult，默认成功使用ResultStatuEnum.SUCCESS
     * code: 200
     * msg: 操作成功
     *
     * @param data 返回前端的业务数据对象
     * @return
     */
    public static ResultVO success(Object data) {
        return success(ResultCommonEnum.SUCCESS, data);
    }

    /**
     * 返回成功
     *
     * @param iResult IResult的实现类，定义返回的状态码及code
     * @param data    返回前端的业务数据对象
     * @return
     */
    public static ResultVO success(IResultEnum iResult, Object data) {
        ResultVO resultVO = new ResultVO(iResult);
        resultVO.setData(data);
        return resultVO;
    }

//    /**
//     * 返回操作错误
//     *
//     * @return
//     */
//    public static ResultVO error() {
//        return new ResultVO(ResultCommonEnum.UNKNOWN);
//    }

    /**
     * 返回未知错误
     *
     * @return
     */
    public static ResultVO error(Exception e) {
        return new ResultVO(ResultCommonEnum.UNKNOWN, e);
    }

    /**
     * 返回未知错误
     *
     * @return
     */
    public static ResultVO error(Object o) {
        return new ResultVO(ResultCommonEnum.UNKNOWN, o);
    }

    /**
     * 返回操作错误
     *
     * @return
     */
    public static ResultVO error(IResultEnum iResult, Object data) {
        return new ResultVO(iResult, data);
    }


    /**
     * 返回操作错误
     *
     * @return
     */
    public static ResultVO error(IResultEnum iResult) {
        return new ResultVO(iResult);
    }



}
