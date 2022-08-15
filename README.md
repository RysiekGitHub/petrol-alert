# petrol-alert

Mysql Deployment: (run from src/kubernetes)
- kubectl apply -f mysql-secret.yaml
- kubectl apply -f mysql-configmap.yaml
- kubectl apply -f mysql-credentials.yaml
- kubectl apply -f mysql-storage.yaml
- kubectl apply -f mysql-deployment.yaml

Application deployment:
From project main catalog:
- ./gradlew assemble
- ./gradlew docker
- check that image is inside docker: docker images
Run from src/kubernetes:
- kubectl apply -f deployment.yaml
- check that pods is working: kubectl get pods

Make application work properly:
- kubectl exec --stdin --tty [mysql-pod-name] -- /bin/bash
- mysql -u test -p
-- enter "test" password
- enter: "use petrolalert"  
- create table petrol_station(name VARCHAR(255));
- application should be ready to work properly

Application:
- http://localhost:32764/stations - get method - return list stations
- http://localhost:32764/stations - post method - add record with station name 

Run Kafka: 
1) deploy Zookeeper to track its configuration of what topics are added or removed to served subscribers: 
- kubectl create -f zookeeper-deployment.yaml 

2) create service for Zookeeper and route traffic to the Zookeeper pod: 
- kubectl create -f zookeeper-service-deployment.yaml

3) deploy kafka:
- kubectl create -f kafka-deployment.yaml

4) Deploying Kafka Broker: 
- kubectl create -f kafka-broker-deployment.yaml

admintome-test:1:1 topic should be created


Example JSON data for database: 

        {
            "stationName": "test",
            "localization": {
            "lon": 2.0,
            "lat": 2.1
        },
        "petrolPrices": {
            "PB95": {
            "name": "PB95",
            "price": 7.0,
            "currency": "PLN"
        },
         "PB98": {
            "name": "PB98",
            "price": 8.45,
            "currency": "PLN"

        },
         "Diesel": {
            "name": "Diesel",
            "price": 6.0,
            "currency": "PLN"

        },
        "LPG": {
            "name": "LPG",
            "price": 8.0,
            "currency": "PLN"

        },
         "DieselUltimate": {
            "name": "DieselUltimate",
            "price": 6.40,
            "currency": "PLN"
        }
    }
}
