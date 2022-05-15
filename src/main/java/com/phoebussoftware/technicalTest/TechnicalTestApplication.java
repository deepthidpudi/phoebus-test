package com.phoebussoftware.technicalTest;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class TechnicalTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(TechnicalTestApplication.class, args);
  }
  
  @Bean
  public ModelMapper modelMapper() {
   return new ModelMapper();
  }
}
