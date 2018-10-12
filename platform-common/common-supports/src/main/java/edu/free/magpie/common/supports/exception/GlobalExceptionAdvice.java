package edu.free.magpie.common.supports.exception;

import edu.free.magpie.common.supports.transform.IResultEnum;
import edu.free.magpie.common.supports.transform.Result;
import edu.free.magpie.common.supports.transform.ResultCommonEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * project: sneb
 * Description： TODO
 *
 * @author: ryan
 * @create: Created in 2018/6/22 9:07
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/22
 * Version: 0.0.1
 * Modified By:
 */
@ControllerAdvice
public class GlobalExceptionAdvice extends SnebExceptionHandlerAdvice {

    @Value("${global.error.defaultView:snebErrorView}")
    private String defaultErrorView;

    @Override
    public boolean isReturnJson(Class clazz) {
        return true;
    }

    @Override
    public ResponseEntity returnJson(IResultEnum iResultEnum, Exception exception) {
        if(null == iResultEnum){
            ResultCommonEnum resultCommonEnum = ResultCommonEnum.UNKNOWN;
            resultCommonEnum.setMsg(exception.getMessage());
            return ResponseEntity.ok(Result.error(resultCommonEnum,exception));
        }
        return ResponseEntity.ok(Result.error(iResultEnum,exception));
    }


    @Override
    public ModelAndView returnModelAndView(HttpServletRequest request,IResultEnum iResultEnum, Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        if(null != iResultEnum){
            modelAndView.addObject("errorCode",iResultEnum.getCode());
            modelAndView.addObject("errorTitle",iResultEnum.getTitle());
            modelAndView.addObject("errorMsg",iResultEnum.getMsg());
        }else{
            modelAndView.addObject("errorCode",ResultCommonEnum.UNKNOWN.getCode());
            modelAndView.addObject("errorTitle",iResultEnum.getTitle());
            modelAndView.addObject("errorMsg",exception.getMessage());
        }
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName("/" + defaultErrorView);
        return modelAndView;
    }
}
