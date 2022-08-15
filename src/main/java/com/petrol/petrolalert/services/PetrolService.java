package com.petrol.petrolalert.services;

import com.petrol.petrolalert.PetrolName;
import com.petrol.petrolalert.models.Petrol;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Service

public class PetrolService {

  public double getPriceByPetrolName(PetrolName petrolName, HashMap<PetrolName, Petrol> petrolPrices) {
    if (petrolPrices.get(petrolName) != null) {
      return petrolPrices.get(petrolName).getPrice();
    }
    return -1;
  }

  public Petrol mapPetrol(PetrolName name, ResultSet rs) throws SQLException {
    return new Petrol(
        name,
        rs.getDouble(name.toString()),
        "PLN"
    );
  }
}
