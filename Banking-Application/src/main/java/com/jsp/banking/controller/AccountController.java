package com.jsp.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.jsp.banking.entity.Account;
import com.jsp.banking.service.AccountService;
import com.jsp.banking.utility.ResponseStructure;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	// 1.Add Account REST API
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Account>> createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}
	
	// 2.Get Account REST API
	
	@GetMapping("/{id}")     // directly give sId in URL localhost:8080/students/101
	public ResponseEntity<ResponseStructure<Account>> getStudent(@PathVariable long id) {
		return accountService.getAccount(id);
	}
	
	// 3.Deposit REST API
	
	@PutMapping("/deposit/{id}/{amount}")
	public ResponseEntity<ResponseStructure<Account>> deposit(@PathVariable long id, @PathVariable double amount) {
		return accountService.deposit(id, amount);
	}
	
	// 4.Withdraw REST API
	
	@PutMapping("/withdraw/{id}/{amount}")
	public ResponseEntity<ResponseStructure<Account>> withdraw(@PathVariable long id, @PathVariable double amount) {
		return accountService.withdraw(id, amount);
	}
	
	// 5. Get All Accounts REST API
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<Account>>> getAllAccounts() {
		return accountService.getAllAccounts();
	}
	
	
	// 6.Delete Account REST API
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Account>> deleteAccount( @PathVariable long id) {
		return accountService.deleteAccount(id);
	}
	
	
}
