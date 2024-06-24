package com.example.aop.accountdao;

import com.example.aop.model.Account;
import java.util.List;

public interface AccountDao {

    void saveAccount(Account account,Account account2);

    List<Account> findAccounts();
    void findAccountsWithException();
}
