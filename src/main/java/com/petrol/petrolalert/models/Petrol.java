package com.petrol.petrolalert.models;

import com.petrol.petrolalert.PetrolName;

import java.util.Objects;

public class Petrol {
  private Double price;
  private String currency;

  public Petrol() {
  }

  public Petrol(Double price, String currency) {
    this.price = price;
    this.currency = currency;
  }

  public double getPrice() {
    return price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Petrol petrol = (Petrol) o;
    return Objects.equals(price, petrol.price) && Objects.equals(currency, petrol.currency);
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public int hashCode() {
    return Objects.hash(price, currency);
  }

  @Override
  public String toString() {
    return "Petrol{" +
        "price=" + price +
        ", currency='" + currency + '\'' +
        '}';
  }
}
