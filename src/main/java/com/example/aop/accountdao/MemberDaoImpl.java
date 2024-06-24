package com.example.aop.accountdao;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao{
    @Override
    public void saveAccount() {
        System.out.println(getClass()+"Method working in saving account");

    }
}
