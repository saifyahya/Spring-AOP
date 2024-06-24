package com.example.aop.accountaop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)  // this order for advices than uses the same pointcut in other aspect classes to determine the priority
public class LoggingAspect {

    @Before("execution(* com.example.aop.accountdao.*.save*(com.example.aop.model.Account,com.example.aop.model.Account))")
    public void logging()
    {
        System.out.println("Logging: before execution of saveAccount()");
    }



    @Before("anyReturnTypeAnyMethodAnyArgsInAccountDaoPackage()")
    public void logging2(){
        System.out.println("Logging: before execution of any method in account dao package");
    }


    @Before("com.example.aop.accountaop.SharedPointCutExpressions.anyNonGetter_SetterMethod()")  // the point cut used here is from sharedpoinTcutexpressions class, must provide the class full reference path
    public void myLogging(){
        System.out.println("Logging: Any non getter / setter method in Logging aspect");
    }

}
