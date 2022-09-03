package com.petrol.petrolalert.models;
import com.petrol.petrolalert.PetrolName;
import java.util.HashMap;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PetrolStation that = (PetrolStation) o;
    return Objects.equals(stationName, that.stationName) && Objects.equals(petrolPrices, that.petrolPrices) && Objects.equals(localization, that.localization);
  }

  @Override
  public String toString() {
    return "PetrolStation{" +
        "stationName='" + stationName + '\'' +
        ", petrolPrices=" + petrolPrices +
        ", localization=" + localization +
        '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(stationName, petrolPrices, localization);
  }
}
