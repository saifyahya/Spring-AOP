package com.example.aop.accountaop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SharedPointCutExpressions {

    @Pointcut("execution(* com.example.aop.accountdao.*.get*(..))")
    public void anyGetterMethod(){};

    @Pointcut("execution(* com.example.aop.accountdao.*.set*(..))")
    public void anySetterMethod(){};

    @Pointcut("execution(* com.example.aop.accountdao.*.*(..))")
    public void anyMethodInDaoPackage(){};

    @Pointcut("anyMethodInDaoPackage() && !(anySetterMethod() || anyGetterMethod())")
    public void anyNonGetter_SetterMethod(){};

    @Pointcut("execution(* com.example.aop.accountdao.AccountDaoImpl.findAccounts())")
    public void findAccountsPointcut(){};

    @Pointcut("execution(* com.example.aop.accountdao.AccountDaoImpl.findAccountsWithException())")
    public void findAccountsWithExceptionPointcut(){};

    @Pointcut("execution(* com.example.aop.accountdao.*.*(..))")
    public void anyReturnTypeAnyMethodAnyArgsInAccountDaoPackage(){};

    @Pointcut("execution(String com.example.aop.service.*.getBankInfo())")
    public void bankInfoPointCut(){}

    @Pointcut("execution(String com.example.aop.service.*.getBankInfoWithException())")
    public void bankInfoPointCutWithException(){}
}
