package com.example.aop;

import com.example.aop.accountdao.AccountDao;
import com.example.aop.accountdao.MemberDao;
import com.example.aop.model.Account;
import com.example.aop.service.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AccountDao accountDao,MemberDao memberDao, BankService bankService){
		return runner->{
			appAccountDemo(accountDao,memberDao,bankService);
		};
	}

	void appAccountDemo(AccountDao accountDao, MemberDao memberDao, BankService bankService){
		Account acc1= new Account();
		acc1.name="saif1";
		acc1.number=101;
		Account acc2= new Account();
		acc2.name="saif2";
		acc2.number=102;
		accountDao.saveAccount(acc1,acc2);
		memberDao.saveAccount();
		System.out.println("Calling the method in the main app: "+accountDao.findAccounts());

try {
	accountDao.findAccountsWithException();
}
catch (Exception e){
	System.out.println("An exception is caught from thr main program");
}


		System.out.println("Calling bank info from the main app "+bankService.getBankInfo());
		try {
			bankService.getBankInfoWithException();

		}catch (Exception e){
			System.out.println("Exception caught in main app "+e.getMessage());
		}

	}

}
