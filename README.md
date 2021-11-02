# Stock demo CI/CD pipeline using Java Spring

Stock application with CI/CD pipeline 

## Application overview

### Features

 - Get all stocks
 - Get stock by id
 - Update Stock
 - Add stock
 - Delete stock
 
### Endpoints

 - `"/api/stocks"` get all the stocks 
 - `"/api/stocks/1"` get the stock by id
 - `"/api/stocks/1"` delete the stock by id
 - `"/api/stocks"` post the stock with json object in request body.
 - `"/api/stocks/1"` patch the stock with json object in request body.
 ### Persistence
 
 Persistence for this project is set up using Spring Data JPA, and utilizes `h2` database,
 which is a runtime database for the ease of testing and continuous integration, however is fully compatible with many 
 standard database technologies like Postgres.
 
 There is a single database entity [Stock](/stockapp/src/main/java/com/payconiq/stockapp/model/Stock.java)
  and a corresponding repository [StockRepository](/stockapp/src/main/java/com/payconiq/stockapp/repository/StockRepository.java)

### Tests

  Tests are separated into two classpaths (in order to run them as separate tasks): 
   - [src/test](src/test) holds the unit tests
   - [src/it](src/it) holds the integration tests, in this case repository and rest endpoint tests.


## CI/CD pipeline on Semaphore

The Stock pipeline is configured to:

  1. Clone project
  2. Build the project
  3. Build Docker image
  4. Push image to `hub.docker.com`

## Build configuration

This project is set up using Maven. Build configuration can be found at `pom.xml`.

##### Running the project

	`mvn clean install -Dmaven.test.skip=true`
 
	`mvn spring-boot:run`

	
