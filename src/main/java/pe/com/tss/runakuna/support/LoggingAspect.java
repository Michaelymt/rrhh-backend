package pe.com.tss.runakuna.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by josediaz on 28/10/2016.
 */
@Aspect
@Component("loggingAspect")
public class LoggingAspect {
    protected final Logger logger = LoggerFactory.getLogger(getClass());


    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Pointcut("bean(*Controller)")
    private void controllerBean() {
    }

    @Pointcut("bean(*RepositoryImpl)")
    private void repositoryBean() {
    }

    @Pointcut("execution(public void *())")
    private void publicMethodActionListener() {
    }

    @Pointcut("execution(public * *(..))")
    private void publicMethod() {
    }

    @Pointcut("execution(private * *(..))")
    private void privateMethod() {
    }


    @Around("(controllerBean() || repositoryBean())&& (publicMethodActionListener()|| publicMethod() || privateMethod())")
    public Object logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method =proceedingJoinPoint.getSignature().toShortString();

        StopWatch stopWatch = new StopWatch(method);
        logger.info("START AUTO-LOG FOR:"+method+ "("+ AspectUtil.getParameterTypes(proceedingJoinPoint)+")");

        logger.debug("  PARAMS."+method+":"+AspectUtil.getParameterValues(proceedingJoinPoint));

        try {
            stopWatch.start(method);
            Object obj = proceedingJoinPoint.proceed();
            logger.info("END AUTO-LOG FOR:" + method);

            return obj;
        } catch (Throwable throwable) {
            logger.error("Error auto logging method: " + method, throwable);
            throw throwable;
        }finally {
            stopWatch.stop();
            logger.debug(stopWatch.shortSummary());
        }
    }


}