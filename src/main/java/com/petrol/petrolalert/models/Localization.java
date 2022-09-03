package com.petrol.petrolalert.models;

import java.util.Objects;

public class Localization {
  private final double lon;
  private final double lat;


  public Localization(double lon, double lat) {
    this.lon = lon;
    this.lat = lat;
  }

  public double getLong() {
    return lon;
  }

  public double getLat() {
    return lat;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Localization that = (Localization) o;
    return Double.compare(that.lon, lon) == 0 && Double.compare(that.lat, lat) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(lon, lat);
  }

  @Override
  public String toString() {
    return "Localization{" +
        "lon=" + lon +
        ", lat=" + lat +
        '}';
  }
}
