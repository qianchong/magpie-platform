package edu.free.magpie.common.supports.exception;

import edu.free.magpie.common.supports.transform.IResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * project: sneb
 * Description： 抽像全局日志处理类
 *
 * @author: ryan
 * @create: Created in 2018/6/7 15:19
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/7
 * Version: 0.0.1
 * Modified By:
 */
public abstract class SnebExceptionHandlerAdvice<T> {
    public final Logger logger = LoggerFactory.getLogger(SnebExceptionHandlerAdvice.class);

    /**
     * 处理所有异常
     * @param request
     * @param exception
     * @param cause
     * @param handlerMethod
     * @return
     */
    @ExceptionHandler(Exception.class)
    protected T handleException(HttpServletRequest request,
                             Exception exception,
                             Throwable cause,
                             HandlerMethod handlerMethod) {
        logError(request,handlerMethod,exception);
        otherOperate(request, exception);
        if (isReturnJson(handlerMethod.getMethod().getReturnType())) {
            return (T) returnJson(null, exception);
        } else {
            return (T) returnModelAndView(request,null ,exception);
        }
    }

    @ExceptionHandler(BizException.class)
    protected T handleException(HttpServletRequest request,
                                BizException exception,
                                Throwable cause,
                                HandlerMethod handlerMethod) {
        logError(request,handlerMethod,exception);
        otherOperate(request, exception);
        if (isReturnJson(handlerMethod.getMethod().getReturnType())) {
            return (T) returnJson(exception.getErrorResult(), exception);
        } else {
            return (T) returnModelAndView(request,exception.getErrorResult(), exception);
        }
    }

    private void logError(HttpServletRequest request,HandlerMethod handlerMethod,Exception e){
        MethodParameter[] args = handlerMethod.getMethodParameters();
        String params = "";
        for (MethodParameter methodParameter : args) {
            System.out.println(methodParameter.getParameterName());
            // params += JSON.toJSONString(o);
        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        logger.error("系统异常:{} " ,e);
    }

    public void otherOperate(HttpServletRequest request, Exception exception){

    }

//    @ModelAttribute
//    public Object getBobyInfo(HttpEntity<String> httpEntity, HttpServletRequest request, HttpServletResponse response){
//        //获取参数
//        String data = httpEntity.getBody();
//        System.out.println(data);
//        ObjectMapper objectMapper =  new ObjectMapper();
//        ParamBean paramBean = data != null ? objectMapper.readValue(data, ParamBean.class) : null;
//        if (pb != null)
//        {
//            String secretId = paramBean.getSecretId();
//            String token = paramBean.getToken();
//            if(StringUtils.isBlank(secretId)){
//                throw new ValidateException( Enums.ERROR_SECRETID_NOTNULL,
//                        "SECRETID不能为空");
//            }else if(StringUtils.isBlank(token)){
//                throw new ValidateException( Enums.ERROR_TOKEN_NOT_NULL,
//                        "token不能为空");
//            }else if(validate(token)){
//                throw new ValidateException( Enums.ERROR_TOKEN_INVALID,
//                        "无效的token");
//            }
//        }
//        return null;
//    }

    /**
     *  通过判断方法返回值的类型判断是否返回json数据类型还是modelAndView类型，判断是否还回json类型的数据，
     *  ex: aClass.getName().equals(ResponseEntity.class.getName());
     * @param clazz 方法返回值的类型
     * @return
     */
    public abstract boolean isReturnJson(Class clazz);

    /**
     * 构建返回json返回类型的返回内容.
     *  ex: return ResponseEntity.ok(e.getMessage());
     * @param iResultEnum
     * @param exception
     * @return
     */
    public abstract ResponseEntity returnJson(IResultEnum iResultEnum, Exception exception);

    /**
     * 构建返回ModelAndView类型的返回内容.
     * ex
     *     ModelAndView mav = new ModelAndView();
     *     mav.addObject("exception", e);
     *     mav.addObject("url", request.getRequestURL());
     *     mav.setViewName("/" + defaultErrorView);
     *     return mav;
     * @param request
     * @param exception
     * @return
     */
    public abstract ModelAndView returnModelAndView(HttpServletRequest request, IResultEnum iResultEnum,Exception exception);
}
