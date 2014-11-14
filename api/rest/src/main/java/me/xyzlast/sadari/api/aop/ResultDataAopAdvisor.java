package me.xyzlast.sadari.api.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * Created by ykyoon on 14. 11. 7.
 */
@Component
@Aspect
public class ResultDataAopAdvisor {
    private static Logger logger = LoggerFactory.getLogger("ResultData");
    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    private void restController() {

    }

    @Around("restController()")
    public Object wrapResponseObject(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object ret = pjp.proceed();
            return new ResultData(ret);
        } catch(Exception ex) {
            logger.error("ResultData", ex);
            return new ResultData(ex);
        }
    }
}
