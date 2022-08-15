package com.petrol.petrolalert.models;

import com.petrol.petrolalert.PetrolName;

public class Petrol {
  private final PetrolName name;
  private final Double price;
  private final String currency;

  public Petrol(PetrolName name, Double price, String currency) {
    this.name = name;
    this.price = price;
    this.currency = currency;
  }

  public double getPrice() {
    return price;
  }

}
