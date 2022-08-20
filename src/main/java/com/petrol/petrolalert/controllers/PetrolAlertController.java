package com.petrol.petrolalert.controllers;

import com.petrol.petrolalert.models.PetrolStation;
import com.petrol.petrolalert.services.PetrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PetrolAlertController {

  private final PetrolService petrolService;

  @Autowired
  public PetrolAlertController(PetrolService petrolService) {
    this.petrolService = petrolService;
  }

  @PostMapping("/stations")
  public ResponseEntity<String> addStation(@RequestBody PetrolStation petrolStation) {
    petrolService.addPetrolStation(petrolStation);
    return ResponseEntity.accepted().build();
  }

  @GetMapping("/stations/{name}")
  public ResponseEntity<PetrolStation> getStation(@PathVariable String name) {
    final PetrolStation station = petrolService.getPetrolStations(name);
    return ResponseEntity.ok(station);
  }
}
