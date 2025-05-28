package com.zby.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
/*@Component
@Aspect*/
public class ProjectAdvice {
    @Pointcut("execution(* com.zby.service.*Service.*(..))")
    private void servicePt(){

    }
    @Around("ProjectAdvice.servicePt()")
    public void runSpeed(ProceedingJoinPoint pjp) throws Throwable {
        long l = System.currentTimeMillis();
        //获取签名信息
        Signature signature = pjp.getSignature();
        String className=signature.getDeclaringTypeName();
        String methodName=signature.getName();

        System.out.println();
        for (int i = 0; i < 10000; i++) {
            pjp.proceed();
        }
        long l2=System.currentTimeMillis();
        System.out.println(className+"."+methodName+"万次执行时间： "+(l2-l)+"ms");


    }
}
