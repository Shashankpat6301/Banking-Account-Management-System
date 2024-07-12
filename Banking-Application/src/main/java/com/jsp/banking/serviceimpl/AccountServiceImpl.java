package com.jsp.banking.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.banking.entity.Account;
import com.jsp.banking.exception.AccountNotFoundByIdException;
import com.jsp.banking.repository.AccountRepository;
import com.jsp.banking.service.AccountService;
import com.jsp.banking.utility.ResponseStructure;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public ResponseEntity<ResponseStructure<Account>> createAccount(Account account) {
		Account a1=accountRepository.save(account);
		
		ResponseStructure<Account>responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Account created successfully!!!");
		responseStructure.setData(a1);
		
		return new ResponseEntity<ResponseStructure<Account>>(responseStructure,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<Account>> getAccount(long id) {
		Optional<Account>optional=accountRepository.findById(id);
		
		if(optional.isPresent()) {
			Account account=optional.get();
			
			ResponseStructure<Account>responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Account found successfully!!!");
			responseStructure.setData(account);
			
			return new ResponseEntity<ResponseStructure<Account>>(responseStructure,HttpStatus.FOUND);
		}
		else {
			throw new AccountNotFoundByIdException("Account not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Account>> deposit(Long id, double amount) {
		Optional<Account>optional=accountRepository.findById(id);
		
		if(optional.isPresent()) {
			Account existingAccount=optional.get();
			double total=existingAccount.getBalance()+ amount;
			existingAccount.setBalance(total);
			Account savedAccount=accountRepository.save(existingAccount);
			
			ResponseStructure<Account>responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Amount deposited successfully!!!");
			responseStructure.setData(savedAccount);
			
			return new ResponseEntity<ResponseStructure<Account>>(responseStructure,HttpStatus.OK);
		}
		else {
			throw new AccountNotFoundByIdException("Account not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Account>> withdraw(long id, double amount) {
		Optional<Account>optional=accountRepository.findById(id);
		
		if(optional.isPresent()) {
			Account existingAccount=optional.get();
			double total=existingAccount.getBalance()- amount;
			existingAccount.setBalance(total);
			Account savedAccount=accountRepository.save(existingAccount);
			
			ResponseStructure<Account>responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Amount withdrawn successfully!!!");
			responseStructure.setData(savedAccount);
			
			return new ResponseEntity<ResponseStructure<Account>>(responseStructure,HttpStatus.OK);
		}
		else {
			throw new AccountNotFoundByIdException("Account not found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Account>>> getAllAccounts() {
		List<Account> accounts=accountRepository.findAll();
		
		if(accounts !=null) {
			ResponseStructure<List<Account>> responseStructure=new ResponseStructure<List<Account>>();
			responseStructure.setStatus(HttpStatus.FOUND.value());                                           
			responseStructure.setMessage("All accounts data found successfully!!!");                           
			responseStructure.setData(accounts);                                                               
			return new ResponseEntity<ResponseStructure<List<Account>>>(responseStructure,HttpStatus.FOUND);   
		}
		else {
			throw new AccountNotFoundByIdException("Account not found");
		}
		
	}

	@Override
	public ResponseEntity<ResponseStructure<Account>> deleteAccount(long id) {
		Optional<Account>optional=accountRepository.findById(id);
		
		if(optional.isPresent()) {
			Account account=optional.get();
			accountRepository.delete(account);
			
			ResponseStructure<Account> responseStructure=new ResponseStructure();
			responseStructure.setStatus(HttpStatus.OK.value());                                         
			responseStructure.setMessage("Account Deleted successfully!!!");                               
			responseStructure.setData(account);                                                            
			return new ResponseEntity<ResponseStructure<Account>>(responseStructure,HttpStatus.OK);      
		}
		else {
			throw new AccountNotFoundByIdException("Account not found");
		}
	}
	
	


}
