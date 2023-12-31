# Drone Service API

This project implements a RESTful API service for managing a fleet of drones capable of carrying medications. 
The API allows clients to communicate with the drones through a dispatch controller.

## Table of Contents

- [Requirements](#requirements)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Getting Started](#getting-started)
  - [Build and Run](#build-and-run)
  - [Test](#test)
- [API Endpoints](#api-endpoints)
- [API Documentation](#api-documentation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Requirements

### Functional Requirements

- There is no need for UI.
- Prevent the drone from being loaded with more weight than it can carry.
- Prevent the drone from being in LOADING state if the battery level is below 25%.
- Introduce a periodic task to check drone battery levels and create a history/audit event log for this.

### Non-functional Requirements

- Input/output data must be in JSON format.
- The project must be buildable and runnable.
- The project must have a README file with build/run/test instructions.
- Any data required by the application to run must be preloaded in the database.
- JUnit tests are mandatory.

## Project Structure
http://localhost:8080/swagger-ui/index.html#/

## Dependencies

- Spring Boot Starter Web for RESTful API
- Spring Boot Starter Data JPA for data access
- H2 Database for in-memory database (replace with your preferred database)
- Spring Boot Starter Test for testing
- SpringDoc OpenAPI for API documentation
Getting Started
Build and Run
Clone the repository:

git clone https://github.com/richodia1/drone-service.git
Navigate to the project directory:
cd drone-service
Build the project:
mvn clean install
Run the application:
The application will be accessible at (http://localhost:8080/swagger-ui/index.html#/)

Test
Run the JUnit tests:
mvn test
API Endpoints
POST /api/drones/register: Register a new drone.
POST /api/drones/load: Load a drone with medication items.
GET /api/drones/{droneId}/medications: Check loaded medication items for a given drone.
GET /api/drones/available: Check available drones for loading.
GET /api/drones/{droneId}/battery: Check drone battery level for a given drone.










