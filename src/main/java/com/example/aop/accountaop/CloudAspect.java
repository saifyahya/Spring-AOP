package com.example.aop.accountaop;


import com.example.aop.model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
@Aspect
@Order(3)
public class CloudAspect {

    @Before("com.example.aop.accountaop.SharedPointCutExpressions.anyNonGetter_SetterMethod()")
    public void apiLogging() {
        System.out.println("Logging: Any non getter / setter method in cloud aspect");
    }

    @Before("com.example.aop.accountaop.SharedPointCutExpressions.anyReturnTypeAnyMethodAnyArgsInAccountDaoPackage()")
    public void provideClearViewLogging(JoinPoint joinPoint) {
        System.out.println();
    }


    @AfterReturning(pointcut = "com.example.aop.accountaop.SharedPointCutExpressions.findAccountsPointcut()", returning = "result")
    public void afterReturningAccounts(List<Account> result) {
        System.out.println("logging: After execution of finding accounts");
        System.out.println("Accounts Founded: " + result);

        result.forEach((acc) -> acc.name = acc.name.toUpperCase());  //process the data after a successfully execution
        System.out.println("after returning: processed accounts to upper case" + result);

        System.out.println("method executed at: " + LocalDateTime.now());
    }

    @AfterThrowing(pointcut = "com.example.aop.accountaop.SharedPointCutExpressions.findAccountsWithExceptionPointcut()", throwing = "theExc")
    public void afterThrowingAnException(JoinPoint joinPoint, Throwable theExc) {

        System.out.println("Logging: After Throwing an exception: " + theExc);
        System.out.println("Method Signature: " + joinPoint.getSignature().toShortString());
    }

    @After("com.example.aop.accountaop.SharedPointCutExpressions.anyMethodInDaoPackage()")
    public void afterMethodCompleted() {
        System.out.println("Logging: After METHOD COMPLETED (@After) ");

    }

    @Around("com.example.aop.accountaop.SharedPointCutExpressions.bankInfoPointCut()")
    public Object getBankInfoAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Logging: @Around: " + joinPoint.getSignature().toShortString());

        long begin = System.currentTimeMillis();
        Object result=null;
        try {
             result = joinPoint.proceed();  //execute the actual method
        }catch(Exception e){
            System.out.println("EXCEPTION CAUGHT INSIDE @Around "+e.getMessage());
        }
        String newResult = "";
        if (result instanceof String) {
            newResult = result + "Intercepted from the @Around advice";
        }

        long end = System.currentTimeMillis();

        long duration = (end - begin) / 1000;
        System.out.println("Method executed in: " + duration+" mins");

        return newResult;
    }


    @Around("com.example.aop.accountaop.SharedPointCutExpressions.bankInfoPointCutWithException()")
    public Object getBankInfoWithExceptionAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Logging: @Around: " + joinPoint.getSignature().toShortString());

        long begin = System.currentTimeMillis();
        Object result = null;
        String newResult = "";

        try {
            result = joinPoint.proceed();  // execute the actual method

            if (result instanceof String) {
                newResult = result + " Intercepted from the @Around advice";
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION CAUGHT INSIDE @Around: " + e.getMessage());
            throw e; // rethrow the exception if needed or handle it appropriately
        }

        long end = System.currentTimeMillis();

        long duration = (end - begin) / 1000;
        System.out.println("Method executed in: " + duration + " seconds");

        return newResult;
    }

}
