package com.petrol.petrolalert.repositories;

import com.petrol.petrolalert.PetrolName;
import com.petrol.petrolalert.interfaces.PetrolStationsRepo;
import com.petrol.petrolalert.models.Localization;
import com.petrol.petrolalert.models.Petrol;
import com.petrol.petrolalert.models.PetrolStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;

@Repository
public class PetrolStationsRepository implements PetrolStationsRepo {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PetrolStationsRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void add(String name, double lat, double lon, double pb95, double pb98, double diesel, double lpg, double dieselUltimate) {

    if (getSerialNumberByStationName(name) == -1) {

      jdbcTemplate.update("INSERT INTO petrol_stations(" +
              "stationName, coordinateLat, coordinateLong) " +
              "values(?,?,?)",
          name, lat, lon);
      int serialNumber = getSerialNumberByStationName(name);

      Timestamp timestamp = Timestamp.from(Instant.now());

      jdbcTemplate.update("INSERT INTO petrol_details(" +
              "PB95, PB98, Diesel, LPG, DieselUltimate, dateCreated, serialNumber) " +
              "values(?,?,?,?,?,?,?)",
          pb95, pb98, diesel, lpg, dieselUltimate, timestamp, serialNumber);
    }
  }

  public PetrolStation getPetrolStations(String stationName) {
    int serialNumber = getSerialNumberByStationName(stationName);

    if (serialNumber > 0) {
      String getDetails = "SELECT * FROM petrol_details WHERE serialNumber = ?";

      return jdbcTemplate.queryForObject(getDetails, (rs, rowNum) -> {
        HashMap<PetrolName, Petrol> petrolPrices = new HashMap<>();
        petrolPrices.put(PetrolName.PB98, new Petrol(rs.getDouble(PetrolName.PB98.toString()), "USD"));
        petrolPrices.put(PetrolName.Diesel, new Petrol(rs.getDouble(PetrolName.Diesel.toString()), "USD"));
        petrolPrices.put(PetrolName.LPG, new Petrol(rs.getDouble(PetrolName.LPG.toString()), "USD"));
        petrolPrices.put(PetrolName.PB95, new Petrol(rs.getDouble(PetrolName.PB95.toString()), "USD"));
        petrolPrices.put(PetrolName.DieselUltimate, new Petrol(rs.getDouble(PetrolName.DieselUltimate.toString()), "USD"));
        return new PetrolStation(
            stationName,
            petrolPrices,
            null
        );
      }, serialNumber);
    }

    return null;

  }

  private int getSerialNumberByStationName(String stationName) {
    String sqlGetPetrolStation = "SELECT * FROM petrol_stations WHERE stationName = ?";
    try {
      return jdbcTemplate.queryForObject(sqlGetPetrolStation, (rs, rowNum) -> rs.getInt("serialNumber"), stationName);

    } catch (EmptyResultDataAccessException e) {
      return -1;
    }
  }
}
