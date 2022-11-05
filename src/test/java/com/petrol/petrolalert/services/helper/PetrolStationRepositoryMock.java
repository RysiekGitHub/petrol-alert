package com.petrol.petrolalert.services.helper;

import com.petrol.petrolalert.PetrolName;
import com.petrol.petrolalert.models.Localization;
import com.petrol.petrolalert.models.Petrol;
import com.petrol.petrolalert.models.PetrolStation;
import com.petrol.petrolalert.interfaces.PetrolStationsRepo;

import java.util.HashMap;

public class PetrolStationRepositoryMock implements PetrolStationsRepo {

  private String name;
  private final HashMap<PetrolName, Petrol> petrolPrices;
  private final Localization localization;


  public PetrolStationRepositoryMock(String name, HashMap<PetrolName, Petrol> petrolPrices, Localization localization) {
      this.name = name;
      this.petrolPrices = petrolPrices;
      this.localization = localization;
  }

  @Override
  public void add(String name, double lat, double lon, double pb95, double pb98, double diesel, double lpg, double dieselUltimate) {
    this.name = name;
  }

  @Override
  public PetrolStation getPetrolStations(String stationName) {
    if (name.equals(stationName)) {
      return new PetrolStation(stationName, petrolPrices, localization);
    }

    System.out.println("Not found station by name: " + stationName);
    return null;
  }

  public String getName() {
    return name;
  }
}
