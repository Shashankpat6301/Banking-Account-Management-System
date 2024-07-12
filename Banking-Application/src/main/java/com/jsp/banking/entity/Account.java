package com.jsp.banking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter      //generate getters
@Setter      //generates setters
@NoArgsConstructor     //generates no argument constructor
@AllArgsConstructor    //generates all argument constructor
@Table(name = "Bank Account")     //to change the table name 
@Entity                          //to map the opject to table
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Acc holder name")
	private String accountHolderName;
	private double balance;
}
