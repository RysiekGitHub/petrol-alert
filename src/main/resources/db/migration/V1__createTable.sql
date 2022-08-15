CREATE TABLE petrol_stations(
                                serialNumber int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                stationName VARCHAR(250) NOT NULL,
                                coordinateLat double NOT NULL,
                                coordinateLong double NOT NULL,
                                PB95 double,
                                PB98 double,
                                Diesel double,
                                LPG double,
                                DieselUltimate double
)
