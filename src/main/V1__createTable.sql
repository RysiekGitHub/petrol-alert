CREATE TABLE petrol_stations(
                                serialNumber int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                stationName VARCHAR(250) NOT NULL,
                                coordinateLat double NOT NULL,
                                coordinateLong double NOT NULL
);
CREATE TABLE petrol_details(
                                detailsId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                PB95 double,
                                PB98 double,
                                Diesel double,
                                LPG double,
                                DieselUltimate double,
                                dateCreated TIMESTAMP(0),
                                serialNumber int, constraint fk foreign key (serialNumber) references petrol_stations(serialNumber)
)
