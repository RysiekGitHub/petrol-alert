package com.petrol.petrolalert.models;

import com.petrol.petrolalert.PetrolName;

public class Petrol {
  private final Double price;
  private final String currency;

  public Petrol(Double price, String currency) {
    this.price = price;
    this.currency = currency;
  }

  public double getPrice() {
    return price;
  }

}
