package edu.free.magpie.common.log.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.free.magpie.common.log.ISnebLog;
import edu.free.magpie.common.log.SimpleSnebLogConfig;
import edu.free.magpie.common.log.annotaion.SnebLogger;
import edu.free.magpie.common.log.entity.SnebLogBO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * project: sneb
 * Description： 日志切面类
 *
 * @author: ryan
 * @create: Created in 2018/6/14 15:19
 * Company: sneb
 * Copyright: 中交二航局　Copyright (c) 2018/6/7
 * Version: 0.0.1
 * Modified By:
 */
@Aspect
public class SenbLoggerAspect {
    public final Logger logger = LoggerFactory.getLogger(SenbLoggerAspect.class);
    public final Logger longLog = LoggerFactory.getLogger("cn.com.sneb.platform.open.common.log.advice.LongLog");

    /** 平台是否接收日志 */
    private boolean platformReceive;

    @Value("${sneb.log.enablelocalLog:false}")
    private boolean enablelocalLog;

    @Value("${sneb.log.longTimeLog:0}")
    private int longTimeLog;

    @Autowired
    private ISnebLog iSysLog;

    private long startTimeMillis = 0;
    private long endTimeMillis = 0;
    private SnebLogBO sysLog;

    @Pointcut("@annotation(edu.free.magpie.common.log.annotaion.SnebLogger)")
    public void loggerPointCut() {
    }

    @Before("loggerPointCut()")
    public void beforeSysLog(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SnebLogger sysLogger = method.getAnnotation(SnebLogger.class);

        sysLog = new SnebLogBO();
        if (sysLogger != null) {
            //注解上的描述
            sysLog.setOperation(sysLogger.value());
        }
        //请求的方法名
        Class targetClass = joinPoint.getTarget().getClass();
        String className = targetClass.getName();
        String methodName = signature.getName();

        sysLog.setMethod(className + "." + methodName + "()");
        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = "";
        for (Object o : args) {
            params += JSON.toJSONString(o);
        }
        if (!StringUtils.isEmpty(params)) {
            sysLog.setParams(params);
        }

        if (iSysLog == null) {
            iSysLog = new SimpleSnebLogConfig();
        }

        //操作人
        String username = iSysLog.operator();
        if (StringUtils.isEmpty(username)) {
            sysLog.setUsername("未知");
        } else {
            sysLog.setUsername(username);
        }

        JSONObject otherInfoMap = iSysLog.otherInfo();
        if (null != otherInfoMap) {
            sysLog.setOtherInfo(otherInfoMap.toJSONString());
        }

        sysLog.setCreateDate(new Date());
    }

    @After("loggerPointCut()")
    public void afterSysLog(JoinPoint joinPoint) {
        endTimeMillis = System.currentTimeMillis();
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTimeMillis);
        long totalTime = (endTimeMillis - startTimeMillis)/1000;
        sysLog.setStartTime(startTime);
        sysLog.setEndTime(endTime);
        sysLog.setTotalTime(totalTime);

        if (enablelocalLog) {
            logger.info("sysLog:{} ", JSON.toJSONString(sysLog));
        }

        if(totalTime >= longTimeLog){
            longLog.info("sysLog:{} ", JSON.toJSONString(sysLog));
        }

        if (platformReceive) {
            //todo: 将日志上传到平台的接口
            // rabbitTemplate.convertAndSend(RabbitConfig.queueName, JSON.toJSONString(sysLog));
        }
    }
}

