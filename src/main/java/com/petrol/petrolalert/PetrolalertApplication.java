package com.petrol.petrolalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PetrolalertApplication {

  public static void main(String[] args) {
    SpringApplication.run(PetrolalertApplication.class, args);
  }

}
