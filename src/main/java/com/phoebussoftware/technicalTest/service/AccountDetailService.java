package com.phoebussoftware.technicalTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoebussoftware.technicalTest.DTO.AccountDTO;
import com.phoebussoftware.technicalTest.DTO.CustomerDTO;
import com.phoebussoftware.technicalTest.model.AccountEntity;
import com.phoebussoftware.technicalTest.model.CustomerEntity;
import com.phoebussoftware.technicalTest.repository.AccountRepository;
import com.phoebussoftware.technicalTest.repository.CustomerRepository;

@Service
public class AccountDetailService {
	@Autowired
	private AccountRepository accRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	public AccountEntity findById(Long accountID) {
		AccountEntity accountEntity=accRepository.findById(accountID);
		return accountEntity;
	}
	
	public AccountEntity saveAccountDetails(AccountDTO accountDTO) {
		
		CustomerEntity customEntity = customerRepository.findById(accountDTO.getCustomerDTO().getCustomerId().longValue());
		AccountEntity accountEntity = convertToEntity(accountDTO);
		if(customEntity != null)
			accountEntity.setCustomEntity(customEntity);
		
		return accRepository.save(accountEntity);
	}
	
	public AccountEntity convertToEntity(AccountDTO accountDTO) {
		 AccountEntity accEntity = new AccountEntity();
		 accEntity.setId(accountDTO.getAccountId());
		 accEntity.setAccountNumber(accountDTO.getAccountNumber());
		 return accEntity;
	  }
	  
	  public AccountDTO convertToDto(AccountEntity accountEntity) {
		  AccountDTO accountDTO = new AccountDTO();
		  accountDTO.setAccountId(accountEntity.getId());
		  accountDTO.setAccountNumber(accountEntity.getAccountNumber());
		  CustomerDTO customerDTO = new CustomerDTO();
		  customerDTO.setCustomerId(accountEntity.getCustomEntity().getId());
		  customerDTO.setDataOfBirth(accountEntity.getCustomEntity().getDataOfBirth());
		  customerDTO.setForeName(accountEntity.getCustomEntity().getForeName());
		  customerDTO.setSurName(accountEntity.getCustomEntity().getSurName());
		  accountDTO.setCustomerDTO(customerDTO);
		  return accountDTO;
	}

}
