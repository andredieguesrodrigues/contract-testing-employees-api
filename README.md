# Employee API for Tests

## Overview

This API was developed aims to be used in contract testing demonstration.

It's an API that provides information related workers registered in a company.

From now the API (/employee) just exposes verbs GET and POST to treat the id, data and check if a employee is a current
employee or not. 

In perspective of contract testing, this API is the consumer and will publish the results of tests everytime that needs to deploy app.

It is necessary to run this application together with its provider (user).

## Requirements

- [*JAVA*](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [*Spring Boot*](https://spring.io/projects/spring-boot)
- [*Maven*](https://maven.apache.org/download.cgi)
- [*Pact*](https://docs.pact.io/)

These listed tools below, can be download in https://mvnrepository.com/ .

## Project Structure

- employees-api
    - scr
        - main
            - java
              - pt
                  - com
                      - employees
                          - config.: Configurations related to swagger and other needs.
                          - controllers.: Controller responsible to provide and expose possibilities to create and query users.
                          - entities.: Database definitions about entities used by controller.
                          - mock.: Class to control wiremock when the app is performed with mock option.
                          - model.: Attributes to represent the model of entities.
                          - repositories.: Interface to interact with data repositories.
                          - EmployeesApiApplication (Main Class)
                      - util.: Support classes.
                        resources.: Definitions of H2 instances and if service will run with real or mocked data.

        - test
            - java
                - pt
                    - com
                      - provider.: Definition of tests by provider
                      

## How to Run

1. Start docker-compose using the command "docker-compose up" to deploy Pact Broker.

2. To simulate a pipeline were created 2 scripts (windows batch files).:

   2.1. 1_run_app.: Inittialy this script just will run the app (in application.properties, you can define if it will use a real or mock data).
   To run it just execute the 1_run_app.bat in the terminal. (cmd/powershell).

   2.1. 2_run_build_provider.: This script will simulate all steps needed to contemplate a contract testing.:
    1. Provider Verification
    2. Create Tag
    3. Can I Deploy to Prod?
    4. Record Deploy to Prod (Considering the simulation of a real deploy)
       To run it just execute the 2_run_build_consumer.bat in the terminal with parameters version and
       tag (e.g .\2_run_build_consumer.bat 1.0 CONS1).
       If you need, is possible perform the commands in a isolated way, just copy/paste and also run in the terminal.
    5. To run these tests and verify locally if contract isn't break, you need to run the provider app (1_run_app.bat).

All results will be stored in Pact Broker (http://localhost:9292/).

## Contract Testing Flow

The flow used in this solution was designed considering local execution but simulating a pipeline flow.

![](imgs/Provider_Flow.png)

## Test Scenarios

![](imgs/Provider_Tests.png)

## Pact Matrix

![](imgs/Pact_Matrix.png)

## References

- [*Pact*](https://docs.pact.io/)
- [*Kreuzwerker Post*](https://kreuzwerker.de/post/introduction-to-consumer-driven-contract-testing)
- [*Baeldung Post*](https://www.baeldung.com/pact-junit-consumer-driven-contracts)

## Contacts and Maintainers

If you have questions or suggestions, please contact the current maintainers.:

-   André Diegues Rodrigues - andrevdrodrigues@gmail.com
