package com.petrol.petrolalert.services;

import com.petrol.petrolalert.PetrolName;
import com.petrol.petrolalert.interfaces.PetrolStationsRepo;
import com.petrol.petrolalert.models.Petrol;
import com.petrol.petrolalert.models.PetrolStation;
import com.petrol.petrolalert.models.PetrolStationDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PetrolService {
  private final PetrolStationsRepo petrolStationsRepository;

  public PetrolService(PetrolStationsRepo petrolStationsRepository) {
    this.petrolStationsRepository = petrolStationsRepository;
  }

  public void addPetrolStation(PetrolStation petrolStation) {
    petrolStationsRepository.add(
        petrolStation.getStationName(),
        petrolStation.getLocalization().getLat(),
        petrolStation.getLocalization().getLon(),
        getPriceByPetrolName(PetrolName.PB95, petrolStation.getPetrolPrices()),
        getPriceByPetrolName(PetrolName.PB98, petrolStation.getPetrolPrices()),
        getPriceByPetrolName(PetrolName.Diesel, petrolStation.getPetrolPrices()),
        getPriceByPetrolName(PetrolName.LPG, petrolStation.getPetrolPrices()),
        getPriceByPetrolName(PetrolName.DieselUltimate, petrolStation.getPetrolPrices())
    );
  }

  public PetrolStation getPetrolStationByName(String stationName) {
    return petrolStationsRepository.getPetrolStations(stationName);
  }

  public List<PetrolStationDto> getAllPetrolStations() {
    return petrolStationsRepository.getAllPetrolStations();
  }

  private double getPriceByPetrolName(PetrolName petrolName, HashMap<PetrolName, Petrol> petrolPrices) {
    if (petrolPrices.get(petrolName) != null) {
      return petrolPrices.get(petrolName).getPrice();
    }
    return 0;
  }

}
