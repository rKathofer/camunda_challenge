# Camunda Challenge

A Spring Boot application that fetches and stores random animal images (cats, dogs, bears) from external APIs.

## Running with Gradle

```bash
./gradlew bootRun
```

Requires PostgreSQL running (see Docker setup below for database only).

## Docker Setup

### Database Only

Start just the PostgreSQL database for local development:

```bash
docker compose up postgres
```

### Full Stack

Build the application image and start all services:

```bash
./gradlew bootBuildImage
docker compose up
```

## Swagger UI

http://localhost:8080/swagger-ui/index.html

---

## Notes

- Images are returned as binary data (rather than base64 encoded)
- Test coverage is limited to one class due to time constraints
- Tried to follow a hexagonal architecture

## Potential Improvements

- **Build Pipeline** - CI/CD setup for automated builds and deployments
- **Package Structure** - e.g. split DTOs from the Controllers
- **Test Coverage** - Add more unit and integration tests
- **Mapping Layer** - Introduce MapStruct or similar for entity/model mapping

## Optional Tasks

### UI Framework Choice

For this project, I would choose **React** due to familiarity. However, any modern framework like Angular or Vue would work well.

### Software Quality

To be discussed during the meeting because of time constraints.
