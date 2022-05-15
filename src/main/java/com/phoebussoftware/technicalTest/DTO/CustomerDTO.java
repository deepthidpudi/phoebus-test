package com.phoebussoftware.technicalTest.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude="accountDTOS")
public class CustomerDTO {
  Long customerId;
  @NotNull(message = "ForeName cannot be null")
  String foreName;
  @NotNull(message = "SurName cannot be null")
  String surName;
  @NotNull(message = "Date Of Birth cannot be null")
  Date dataOfBirth;
  List<AccountDTO> accountDTOS;
}
