package com.petrol.petrolalert.services;
import com.petrol.petrolalert.PetrolName;
import com.petrol.petrolalert.models.Localization;
import com.petrol.petrolalert.models.Petrol;
import com.petrol.petrolalert.models.PetrolStation;
import com.petrol.petrolalert.services.helper.PetrolStationRepositoryMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PetrolServiceTest {

  private PetrolService petrolService;

  @Test
  public void shouldReturnPetrolStationByName() {

    var  stationName = "stationName";
    var lon = 2.2;
    var lat = 1.2;
    var pb95 = 9.2;
    var pb98 = 3.2;
    var diesel = 4.2;
    var dieselUltimate = 7.2;
    var lpg = 1.2;

    var prices = new HashMap<PetrolName, Petrol>();
    prices.put(PetrolName.PB95, new Petrol(pb95, "USD"));
    prices.put(PetrolName.PB98, new Petrol(pb98, "USD"));
    prices.put(PetrolName.Diesel, new Petrol(diesel, "USD"));
    prices.put(PetrolName.LPG, new Petrol(lpg, "USD"));
    prices.put(PetrolName.DieselUltimate, new Petrol(dieselUltimate, "USD"));

    var localization = new Localization(lon, lat);
    var repositoryMock = new PetrolStationRepositoryMock(stationName, prices, localization);
    petrolService = new PetrolService(repositoryMock);

    PetrolStation desiredPetrolStation = petrolService.getPetrolStationByName(stationName);

    assertEquals(desiredPetrolStation, new PetrolStation(stationName, prices, localization));
  }

}
