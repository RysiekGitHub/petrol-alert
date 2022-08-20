package com.petrol.petrolalert.repositories;

import com.petrol.petrolalert.PetrolName;
import com.petrol.petrolalert.models.Localization;
import com.petrol.petrolalert.models.Petrol;
import com.petrol.petrolalert.models.PetrolStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class PetrolStationsRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PetrolStationsRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void add(String name, double lat, double lon, double pb95, double pb98, double diesel, double lpg, double dieselUltimate) {
    jdbcTemplate.update("INSERT INTO petrol_stations(" +
            "stationName, coordinateLat, coordinateLong, PB95, PB98, Diesel, LPG, DieselUltimate) " +
            "values(?,?,?,?,?,?,?,?)",
        name, lat, lon, pb95, pb98, diesel, lpg, dieselUltimate);
  }

  public PetrolStation getPetrolStations(String stationName) {
    String sql = "SELECT * FROM petrol_stations WHERE stationName = ?";

    return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
      HashMap<PetrolName, Petrol> petrolPrices = new HashMap<>();
      petrolPrices.put(PetrolName.PB98, new Petrol(rs.getDouble(PetrolName.PB98.toString()), "USD"));
      petrolPrices.put(PetrolName.Diesel, new Petrol(rs.getDouble(PetrolName.Diesel.toString()), "USD"));
      petrolPrices.put(PetrolName.LPG, new Petrol(rs.getDouble(PetrolName.LPG.toString()), "USD"));
      return new PetrolStation(
          rs.getString("stationName"),
          petrolPrices,
          new Localization(
              rs.getDouble("coordinateLat"),
              rs.getDouble("coordinateLong")
          )
      );
    }, stationName);
  }

  public void editPrice(Long serialNumber, List<Petrol> petrolPrices) {

  }

  public void editName(Long serialNumber, String stationName) {

  }
}
