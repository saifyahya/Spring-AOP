package com.example.aop.accountaop;


import com.example.aop.model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class ApiAspect {
    @Before("com.example.aop.accountaop.SharedPointCutExpressions.anyNonGetter_SetterMethod()")
    public void cloudLogging(JoinPoint joinPoint){
        System.out.println("Logging: Any non getter / setter method in api aspect");

        Signature sign =joinPoint.getSignature();
        System.out.println("Method Signature: "+ sign.toString());

        Object[] args = joinPoint.getArgs();

        for(Object arg:args){
            System.out.println("Arg: "+arg);
            if(arg instanceof Account){
                Account account = (Account)arg;
                System.out.println("Account name: "+account.name);
                System.out.println("Account number: "+account.number);
            }
        }
    }
}
