package com.example.aop.accountdao;

import com.example.aop.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao{


    @Override
    public void saveAccount(Account account,Account account2) {
        System.out.println(getClass()+"Method working in saving account");
    }

    @Override
    public List<Account> findAccounts() {
        Account acc1  =new Account("saif101",101);
        Account acc2  =new Account("saif102",102);
        Account acc3  =new Account("saif103",103);
        return List.of(acc1,acc2,acc3);
    }

    @Override
    public void findAccountsWithException() {
        throw new RuntimeException("Run Time Exception: No Accounts Found");
    }
}
