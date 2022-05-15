package com.phoebussoftware.technicalTest.web;

import com.phoebussoftware.technicalTest.DTO.AccountDTO;
import com.phoebussoftware.technicalTest.model.AccountEntity;
import com.phoebussoftware.technicalTest.service.AccountDetailService;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	public AccountDetailService accDetailService;
	@Autowired
	public ModelMapper modelMapper;

  @GetMapping("/{accountId}")
  public ResponseEntity<AccountDTO> getAccountById(@PathVariable Integer accountId) {
	  AccountEntity accountEntity=accDetailService.findById(new Long(accountId));

    return ResponseEntity.ok(accDetailService.convertToDto(accountEntity));
  }
  
  @GetMapping("/health")
  public String getHealth() {

    return "I am Healthy!!!";
  }
  @PostMapping(path="/accounts",produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AccountDTO> addAccount(@Valid @RequestBody AccountDTO accountDto){
	  System.out.println(accountDto.toString());
	  //System.out.println(accEntity.toString());
	  return ResponseEntity.ok(accDetailService.convertToDto(accDetailService.saveAccountDetails(accountDto)));
  
  }
  
  
}
