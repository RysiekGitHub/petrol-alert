package com.petrol.petrolalert.interfaces;

import com.petrol.petrolalert.models.PetrolStation;
import com.petrol.petrolalert.models.PetrolStationDto;

import java.util.ArrayList;
import java.util.List;

public interface PetrolStationsRepo {

    void add(String name, double lat, double lon, double pb95, double pb98, double diesel, double lpg, double dieselUltimate);

    PetrolStation getPetrolStations(String stationName);

    List<PetrolStationDto> getAllPetrolStations();
}
