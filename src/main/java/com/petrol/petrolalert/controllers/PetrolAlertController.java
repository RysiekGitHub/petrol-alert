package com.petrol.petrolalert.controllers;

import java.sql.SQLException;

import com.petrol.petrolalert.models.PetrolStation;
import com.petrol.petrolalert.repositories.PetrolStationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PetrolAlertController {

  private final PetrolStationsRepository petrolStationsRepository;
  @Autowired
  public PetrolAlertController(PetrolStationsRepository petrolStationsRepository) {
    this.petrolStationsRepository = petrolStationsRepository;
  }

  @PostMapping("/stations")
  public ResponseEntity<String> addStation(@RequestBody PetrolStation petrolStation) {
    petrolStationsRepository.add(petrolStation);
    return ResponseEntity.accepted().build();
  }

  @GetMapping("/stations/{name}")
  public ResponseEntity<PetrolStation> getStation(@PathVariable String name) throws SQLException {
    final PetrolStation station = petrolStationsRepository.getPetrolStations(name);
    return ResponseEntity.ok(station);
  }
}
