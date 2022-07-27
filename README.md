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
