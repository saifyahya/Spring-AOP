package com.example.aop.service;

import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService{


    @Override
    public String getBankInfo() {
        try {
            Thread.sleep(6000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "Test Bank TestSt.";
    }

    @Override
    public void getBankInfoWithException() {
        System.out.println("Exception will be thrown");
        try {
            Thread.sleep(6000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("Bank RunTime Exception");

    }
}
