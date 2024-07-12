package com.jsp.banking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.jsp.banking.entity.Account;
import com.jsp.banking.utility.ResponseStructure;

public interface AccountService {

	public ResponseEntity<ResponseStructure<Account>> createAccount(Account  account);
	
	public ResponseEntity<ResponseStructure<Account>> getAccount(long id);
	
	public ResponseEntity<ResponseStructure<Account>> deposit(Long id, double amount);
	
	public ResponseEntity<ResponseStructure<Account>>  withdraw(long id, double amount);
	
	public ResponseEntity<ResponseStructure<List<Account>>> getAllAccounts();
	
	public ResponseEntity<ResponseStructure<Account>> deleteAccount(long id);
	
	
}
