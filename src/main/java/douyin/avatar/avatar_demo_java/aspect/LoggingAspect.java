package douyin.avatar.avatar_demo_java.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // 定义切点表达式，表示拦截所有类的所有方法
    private static final String POINTCUT_EXPRESSION = "execution(* douyin.avatar.avatar_demo_java.controller..*(..)))";

    // 在方法执行前打印入参日志
    @Before(POINTCUT_EXPRESSION)
    public void logMethodInput(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("Entering request [{}].[{}], parameter: {}", className, methodName, args);
    }

    // 在方法执行后打印出参日志
    @AfterReturning(pointcut = POINTCUT_EXPRESSION, returning = "result")
    public void logMethodOutput(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("Exiting response [{}].[{}], output: {}", className, methodName, result);
    }

}
