package com.petrol.petrolalert.models;
import com.petrol.petrolalert.PetrolName;
import java.util.HashMap;

public class PetrolStation {

  private final String stationName;
  private final HashMap<PetrolName, Petrol> petrolPrices;
  private final Localization localization;

  public PetrolStation(String stationName, HashMap<PetrolName, Petrol> petrolPrices, Localization localization) {
    this.stationName = stationName;
    this.petrolPrices = petrolPrices;
    this.localization = localization;
  }

  public String getName() {
    return stationName;
  }

  public HashMap<PetrolName, Petrol> getPetrolPrices() {
    return petrolPrices;
  }

  public Localization getLocalization() {
    return localization;
  }
}
