package com.phoebussoftware.technicalTest.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AccountDTO {
  Long accountId;
  CustomerDTO customerDTO;
  @NotNull(message = "Account Number cannot be null")
  Integer accountNumber;
}
