## Oddschecker Odds API

**Build app:**
mvn clean install

**Run the app locally:**
mvn spring-boot:run

**Test web services via command line on a mac:**

curl -v -H "Content-Type: application/json" -d '{"betId":1, "userId":"u2", "odds":"1/2"}' http://localhost:8080/odds

curl -v -H "Content-Type: application/json"  http://localhost:8080/odds/1