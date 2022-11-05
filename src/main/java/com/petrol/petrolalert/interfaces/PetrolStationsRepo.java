package com.petrol.petrolalert.interfaces;

import com.petrol.petrolalert.models.PetrolStation;

import java.util.ArrayList;

public interface PetrolStationsRepo {

    void add(String name, double lat, double lon, double pb95, double pb98, double diesel, double lpg, double dieselUltimate);

    PetrolStation getPetrolStations(String stationName);

    ArrayList<PetrolStation> getAllPetrolStations();
}
