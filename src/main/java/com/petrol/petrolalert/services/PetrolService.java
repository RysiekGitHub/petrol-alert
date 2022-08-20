package com.petrol.petrolalert.services;

import com.petrol.petrolalert.PetrolName;
import com.petrol.petrolalert.models.Petrol;
import com.petrol.petrolalert.models.PetrolStation;
import com.petrol.petrolalert.repositories.PetrolStationsRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PetrolService {
  private final PetrolStationsRepository petrolStationsRepository;

  public PetrolService(PetrolStationsRepository petrolStationsRepository) {
    this.petrolStationsRepository = petrolStationsRepository;
  }

  public void addPetrolStation(PetrolStation petrolStation) {
    petrolStationsRepository.add(
        petrolStation.getName(),
        petrolStation.getLocalization().getLat(),
        petrolStation.getLocalization().getLong(),
        getPriceByPetrolName(PetrolName.PB95, petrolStation.getPetrolPrices()),
        getPriceByPetrolName(PetrolName.PB98, petrolStation.getPetrolPrices()),
        getPriceByPetrolName(PetrolName.Diesel, petrolStation.getPetrolPrices()),
        getPriceByPetrolName(PetrolName.LPG, petrolStation.getPetrolPrices()),
        getPriceByPetrolName(PetrolName.DieselUltimate, petrolStation.getPetrolPrices())
    );
  }

  public PetrolStation getPetrolStations(String stationName) {
    return petrolStationsRepository.getPetrolStations(stationName);
  }

  private double getPriceByPetrolName(PetrolName petrolName, HashMap<PetrolName, Petrol> petrolPrices) {
    if (petrolPrices.get(petrolName) != null) {
      return petrolPrices.get(petrolName).getPrice();
    }
    return 0;
  }

}
