package com.petrol.petrolalert.models;

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
}
