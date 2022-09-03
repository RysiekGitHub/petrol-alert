package com.petrol.petrolalert.repositories;

import com.petrol.petrolalert.PetrolName;
import com.petrol.petrolalert.models.Localization;
import com.petrol.petrolalert.models.Petrol;
import com.petrol.petrolalert.models.PetrolStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PetrolStationsRepositoryTest {

  @Autowired
  private PetrolStationsRepository petrolStationsRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void shouldAddPetrolStation() {
    //given
    var name = "petrolName";
    var lat = 1.2;
    var lon = 2.2;
    var pb95 = 5.0;
    var pb98 = 6.0;
    var diesel = 4.5;
    var lpg = 3.0;
    var dieselUltimate = 5.6;

    jdbcTemplate.update("INSERT INTO petrol_stations(" +
            "stationName, coordinateLat, coordinateLong, PB95, PB98, Diesel, LPG, DieselUltimate) " +
            "values(?,?,?,?,?,?,?,?)",
        name, lat, lon, pb95, pb98, diesel, lpg, dieselUltimate);

    //when
    PetrolStation petrolStation = petrolStationsRepository.getPetrolStations(name);
    HashMap<PetrolName, Petrol> petrolPrices = petrolStation.getPetrolPrices();
    Localization localization = petrolStation.getLocalization();

    //then
    assertEquals(name, petrolStation.getName());

    assertEquals(lat, localization.getLat());
    assertEquals(lon, localization.getLong());

    assertEquals(pb95, petrolPrices.get(PetrolName.PB95).getPrice());
    assertEquals(pb98, petrolPrices.get(PetrolName.PB98).getPrice());
    assertEquals(diesel, petrolPrices.get(PetrolName.Diesel).getPrice());
    assertEquals(lpg, petrolPrices.get(PetrolName.LPG).getPrice());
    assertEquals(dieselUltimate, petrolPrices.get(PetrolName.DieselUltimate).getPrice());
  }

  @Test
  public void shouldGetPetrolStationByName() {
    //given
    var name = "test2";
    var lat = 1.2;
    var lon = 2.2;
    var pb95 = 5.0;
    var pb98 = 6.0;
    var diesel = 4.5;
    var lpg = 3.0;
    var dieselUltimate = 5.6;

    jdbcTemplate.update("INSERT INTO petrol_stations(" +
            "stationName, coordinateLat, coordinateLong, PB95, PB98, Diesel, LPG, DieselUltimate) " +
            "values(?,?,?,?,?,?,?,?)",
        name, lat, lon, pb95, pb98, diesel, lpg, dieselUltimate);
    // when
    PetrolStation petrolStation = petrolStationsRepository.getPetrolStations(name);
    //then
    var prices = new HashMap<PetrolName, Petrol>();
    prices.put(PetrolName.LPG, new Petrol(lpg, "USD"));
    prices.put(PetrolName.PB98, new Petrol(pb98, "USD"));
    prices.put(PetrolName.PB95, new Petrol(pb95, "USD"));
    prices.put(PetrolName.Diesel, new Petrol(diesel, "USD"));
    prices.put(PetrolName.DieselUltimate, new Petrol(dieselUltimate, "USD"));

    assertEquals(new PetrolStation(name, prices, new Localization(lon, lat)), petrolStation);
  }
}
