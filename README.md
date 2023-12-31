# Drone Service API

This project provides a RESTful API for managing a fleet of drones carrying medications.

- Requirements: Ensure UI-less, prevent overloading drones, and periodic battery checks.
- Structure: Follows Spring Boot with JPA , H2, and SpringDoc OpenAPI.
- Getting Started: run git Clone https://github.com/richodia1/drone-service.git
- build with Maven, and run on `http://localhost:8080/swagger-ui/index.html#/`.
- Tests: Include JUnit tests for various scenarios.

## API Endpoints

- POST /api/drones/register: Register a new drone.
- POST /api/drones/load: Load a drone with medications.
- GET /api/drones/{droneId}/medications: Check loaded medications for a drone.
- GET /api/drones/available: Check available drones for loading.
- GET /api/drones/{droneId}/battery: Check drone battery level. 
This can be accessed via http://localhost:8080/swagger-ui/index.html#/

## Dependencies

- Spring Boot Starter Web for RESTful API
- Spring Boot Starter Data JPA for data access
- H2 Database for in-memory database (replace with your preferred database)
- Spring Boot Starter Test for testing
- SpringDoc OpenAPI for API documentation



