package com.phoebussoftware.technicalTest.web;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoebussoftware.technicalTest.DTO.AccountDTO;
import com.phoebussoftware.technicalTest.DTO.CustomerDTO;
import com.phoebussoftware.technicalTest.model.CustomerEntity;
import com.phoebussoftware.technicalTest.service.CustomerDetailService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	public CustomerDetailService customDetailService;
	@Autowired
	public ModelMapper modelMapper;

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer customerId) {
	  System.out.println(customerId);
	CustomerDTO customerDTO = customDetailService.convertToDto(customDetailService.findById(new Long(customerId)));
    return ResponseEntity.ok(customerDTO);
  }

  @GetMapping("/account/{customerId}")
  public ResponseEntity<List<AccountDTO>> getAccountsByCustomerId(
      @PathVariable Integer customerId) {
	  
	CustomerEntity customerEntity=customDetailService.findById(new Long(customerId));
  
    List<AccountDTO> accountDTOS = customDetailService.getAccounts(customerEntity);
    return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
  }
  
  @PostMapping(path="/customers",produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerDTO> addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
	  CustomerEntity customerEntity = convertToEntity(customerDTO);
	  try {
		  CustomerEntity customEntityNew = customDetailService.saveCustomerDetails(customerEntity);
		  System.out.println(customEntityNew.toString());
		  return ResponseEntity.ok(convertToDto(customEntityNew));
	  }catch(Exception e) {
		  return new ResponseEntity<>(customerDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  
  
  }
  
  private CustomerEntity convertToEntity(CustomerDTO customerDto) {
	  CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
		return customerEntity;
  }
	  
  private CustomerDTO convertToDto(CustomerEntity customerEntity) {
	  CustomerDTO customerDto = modelMapper.map(customerEntity, CustomerDTO.class);
	  System.out.println(customerEntity.toString());
	  return customerDto;
  }
}
