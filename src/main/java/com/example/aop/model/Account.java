package com.example.aop.model;

public class Account {
    public String name;
    public int number;

    @Override
    public String toString(){
        return "Account: "+name+" - "+number;
    }
   public Account(String name, int number){
        this.name=name;
        this.number=-number;
   }
   public Account(){}
}
