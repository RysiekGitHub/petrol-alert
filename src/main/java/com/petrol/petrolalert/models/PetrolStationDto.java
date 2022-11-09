package com.petrol.petrolalert.models;

import java.util.Objects;

public class PetrolStationDto {

  private final int serialNumber;
  private final String stationName;

  public PetrolStationDto(int serialNumber, String stationName) {
      this.serialNumber = serialNumber;
      this.stationName = stationName;
  }

  public int getSerialNumber() {
    return serialNumber;
  }

  public String getStationName() {
    return stationName;
  }

  @Override
  public String toString() {
    return "PetrolStationDto{" +
        "serialNumber=" + serialNumber +
        ", stationName='" + stationName + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PetrolStationDto that = (PetrolStationDto) o;
    return serialNumber == that.serialNumber && Objects.equals(stationName, that.stationName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serialNumber, stationName);
  }
}
