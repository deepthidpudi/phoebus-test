package com.phoebussoftware.technicalTest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoebussoftware.technicalTest.DTO.AccountDTO;
import com.phoebussoftware.technicalTest.DTO.CustomerDTO;
import com.phoebussoftware.technicalTest.model.AccountEntity;
import com.phoebussoftware.technicalTest.model.CustomerEntity;
import com.phoebussoftware.technicalTest.repository.AccountRepository;
import com.phoebussoftware.technicalTest.repository.CustomerRepository;
@Service
public class CustomerDetailService {
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public CustomerEntity saveCustomerDetails(CustomerEntity customerEntity) {
		CustomerEntity entity = customerRepo.save(customerEntity);
		System.out.println(entity.toString());
		return entity;
	}
	
	public CustomerEntity findById(Long customerId) {
		CustomerEntity customEntity = customerRepo.findById(customerId);
		
		return customEntity;
	}
	
	  
	  public CustomerDTO convertToDto(CustomerEntity customerEntity) {
		  List<AccountEntity> accounts=customerEntity.getAccountEntities();
		  List<AccountDTO> accountDTOs=new ArrayList<AccountDTO>();
		  if(accounts!=null && accounts.size()>0) {
			  for(int i=0;i<accounts.size(); i++) {
				  AccountEntity accountEntity =accounts.get(i);
				  AccountDTO accountDTO=new AccountDTO();
				  accountDTO.setAccountId(accountEntity.getId());
				  accountDTO.setAccountNumber(accountEntity.getAccountNumber());
				  accountDTOs.add(accountDTO);
			  }
		  }
		 CustomerDTO customerDTO=new CustomerDTO();
		 customerDTO.setAccountDTOS(accountDTOs);
		 customerDTO.setCustomerId(customerEntity.getId());
		 customerDTO.setDataOfBirth(customerEntity.getDataOfBirth());
		 customerDTO.setForeName(customerEntity.getForeName());
		 customerDTO.setSurName(customerEntity.getSurName());
		 
		  return customerDTO;
	}
	  
	 public List<AccountDTO> getAccounts(CustomerEntity customerEntity){
		  List<AccountEntity> accounts=customerEntity.getAccountEntities();
		  List<AccountDTO> accountDTOs=new ArrayList<AccountDTO>();
		  if(accounts!=null && accounts.size()>0) {
			  for(int i=0;i<accounts.size(); i++) {
				  AccountEntity accountEntity =accounts.get(i);
				  AccountDTO accountDTO=new AccountDTO();
				  accountDTO.setAccountId(accountEntity.getId());
				  accountDTO.setAccountNumber(accountEntity.getAccountNumber());
				  accountDTOs.add(accountDTO);
			  }
		  }
		  return accountDTOs;
	 }


}
