# Software Developer Coding Challenge

This is a coding challenge for software developer applicants applying through http://work.traderev.com/

# Getting Started
##### Prerequisites
- Java 8
- Maven

#####Framework & Libraries Used:
- Java 8(JDK 8u131)
- Maven3
- Spring Boot 2.0.4
- Swagger (REST API Documentation)
- H2 In-Memory Database Engine
- Apache Web Server

#####Unit Tests:
Spring JUnit

##### URls:
-  URL to access API is http://localhost:8080/
-  URL to access swagger is http://localhost:8080/swagger-ui.html
-  URL to access database is http://localhost:8080/h2

##### How to run project:
- It is maven project so you can import it using its pom file
- It is using spring boot so you can run it by running class CarAuctionSystemApplication because there is main method inside this class.
- It is using embedded H2 in memory database. You can access database using http://localhost:8080/h2 and make sure to use jdbc:h2:mem:testdb as JDBC URL.
- Please refer swagger documentation after running project to find details about all API's.
- Send `POST` request to `http://localhost:8080/bids` url to record a user's bid on a car. Please make sure to create at least one user and car before calling this url and send user id and car id in the request body.
- Send `GET` request to `http://localhost:8080/cars/{carId}/winningBid` url to get the current winning bid for a car.
- Send `GET` request to `http://localhost:8080/cars/{carId}/bids` to get a car's bidding history.

##### Running the tests
> All tests are in test package.

#####References:
- https://spring.io/guides/gs/rest-service/
- http://www.springboottutorial.com/spring-boot-and-spring-jdbc-with-h2

## Goal:

#### You have been tasked with building a simple online car auction system which will allow users to bid on cars for sale and with the following funcitionalies: 

  - [ ] Fork this repo. Keep it public until we have been able to review it.
  - [ ] A simple auction bidding system
  - [ ] Record a user's bid on a car
  - [ ] Get the current winning bid for a car
  - [ ] Get a car's bidding history 

 ### Bonus:

  - [ ] Unit Tests on the above functions
  - [ ] Develop a UI on web or mobile or CLI to showcase the above functionality

## Evaluation:

 - [ ] Solution compiles. Provide a README to run/build the project and explain anything that you leave aside
 - [ ] No crashes, bugs, compiler warnings
 - [ ] App operates as intended
 - [ ] Conforms to SOLID principles
 - [ ] Code is easily understood and communicative
 - [ ] Commit history is consistent, easy to follow and understand
