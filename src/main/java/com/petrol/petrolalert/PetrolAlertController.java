package com.petrol.petrolalert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetrolAlertController {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PetrolAlertController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @GetMapping("/create")
  public void xyz() {
    jdbcTemplate.execute("CREATE TABLE customers(first_name VARCHAR(255))");
  }
}
