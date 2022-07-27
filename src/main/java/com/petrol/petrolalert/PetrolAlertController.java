package com.petrol.petrolalert;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetrolAlertController {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PetrolAlertController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @PostMapping("/stations")
  public ResponseEntity<String> addStation(@RequestBody String name) {
    jdbcTemplate.update("INSERT INTO petrol_station(name) values(?)", name);
    return ResponseEntity.accepted().build();
  }

  @GetMapping("/stations")
  public ResponseEntity<List<String>> getAllStations() {
    final List<String> stations = jdbcTemplate.query("SELECT name from petrol_station", (rs, rowNum) -> rs.getString(1));
    return ResponseEntity.ok(stations);
  }
}
