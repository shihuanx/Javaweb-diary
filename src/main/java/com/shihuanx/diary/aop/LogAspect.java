package com.shihuanx.diary.aop;


import com.alibaba.fastjson.JSONObject;
import com.shihuanx.diary.entity.OperateLog;
import com.shihuanx.diary.mapper.OperateLogMapper;
import com.shihuanx.diary.utils.UserHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LogAspect {

    private static final Logger logger=Logger.getLogger(LogAspect.class.getName());

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.shihuanx.diary.annotation.Log)")

    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        OperateLog operateLog = new OperateLog();

        operateLog.setUserId(UserHolder.getUser().getId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setParams(Arrays.toString(joinPoint.getArgs()));

        //得到消耗时间
        Long beginTime = System.currentTimeMillis();
        Object result=joinPoint.proceed();
        Long endTime = System.currentTimeMillis();

        operateLog.setCostTime(endTime - beginTime);
        operateLog.setReturnValue(JSONObject.toJSONString(result));

        operateLogMapper.insert(operateLog);
        logger.log(Level.INFO,"AOP记录操作日志:{0}",new Object[]{operateLog});
        return result;
    }
}
