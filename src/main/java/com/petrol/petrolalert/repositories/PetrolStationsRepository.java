package com.petrol.petrolalert.repositories;

import com.petrol.petrolalert.PetrolName;
import com.petrol.petrolalert.models.Localization;
import com.petrol.petrolalert.models.Petrol;
import com.petrol.petrolalert.models.PetrolStation;
import com.petrol.petrolalert.services.PetrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class PetrolStationsRepository {
  private final JdbcTemplate jdbcTemplate;
  private final PetrolService petrolService;

  @Autowired
  public PetrolStationsRepository(JdbcTemplate jdbcTemplate, PetrolService petrolService) {
    this.jdbcTemplate = jdbcTemplate;
    this.petrolService = petrolService;
  }

  public void add(PetrolStation petrolStation) {
    jdbcTemplate.update("INSERT INTO petrol_stations(" +
            "stationName, coordinateLat, coordinateLong, PB95, PB98, Diesel, LPG, DieselUltimate) " +
            "values(?,?,?,?,?,?,?,?)",
        petrolStation.getName(),
        petrolStation.getLocalization().getLat(),
        petrolStation.getLocalization().getLong(),
        petrolService.getPriceByPetrolName(PetrolName.PB95, petrolStation.getPetrolPrices()),
        petrolService.getPriceByPetrolName(PetrolName.PB98, petrolStation.getPetrolPrices()),
        petrolService.getPriceByPetrolName(PetrolName.Diesel, petrolStation.getPetrolPrices()),
        petrolService.getPriceByPetrolName(PetrolName.LPG, petrolStation.getPetrolPrices()),
        petrolService.getPriceByPetrolName(PetrolName.DieselUltimate, petrolStation.getPetrolPrices())
    );
  }

  public PetrolStation getPetrolStations(String stationName) throws SQLException {
    String sql = "SELECT * FROM petrol_stations WHERE stationName = ?";
      // why it doesn't work?
      //    new HashMap<>()
      //        .put(PetrolName.PB95, petrolService.mapPetrol(PetrolName.PB95, rs))
      //        .put(PetrolName.PB98, petrolService.mapPetrol(PetrolName.PB98, rs))
      //        .put(PetrolName.Diesel, petrolService.mapPetrol(PetrolName.Diesel, rs))
      //        .put(PetrolName.DieselUltimate, petrolService.mapPetrol(PetrolName.DieselUltimate, rs))
      //        .put(PetrolName.LPG, petrolService.mapPetrol(PetrolName.LPG, rs))
      //),

    return jdbcTemplate.queryForObject(sql, new Object[]{ stationName }, (rs, rowNum) ->
        new PetrolStation(
            rs.getString("stationName"),
            new HashMap<PetrolName, Petrol>(),
            new Localization(
                rs.getDouble("coordinateLat"),
                rs.getDouble("coordinateLong")
            )
        ));
  }

  public void editPrice(Long serialNumber, List<Petrol> petrolPrices) {

  }

  public void editName(Long serialNumber, String stationName) {

  }
}
