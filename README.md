## Running the Project with Docker

This project is containerized using Docker and Docker Compose for easy setup and deployment. Below are the instructions and details specific to this project:

### Project-Specific Docker Requirements
- **Java Version:** Uses Eclipse Temurin JDK 17 for building and JRE 17 for running the application (as specified in the Dockerfile).
- **Build Tool:** Maven Wrapper (`mvnw`) is used for building the project inside the container.

### Environment Variables
- An `.env` file is present but currently empty. If your application requires environment variables, add them to `.env` and uncomment the `env_file` line in `docker-compose.yml`.

### Build and Run Instructions
1. **Build and start the application:**
   ```sh
   docker compose up --build
   ```
   This will build the Docker image and start the `java-app` service.

2. **Access the application:**
   - The application will be available at [http://localhost:8080](http://localhost:8080) by default.

### Ports
- **8080:** Exposed by the `java-app` service (default Spring Boot port).

### Special Configuration
- The Dockerfile creates a non-root user (`appuser`) for running the application securely.
- No external services (like databases) are configured by default. If your application requires additional services, uncomment and configure the relevant sections in `docker-compose.yml`.
- The `.env` file is included for environment variable management but is empty by default.

### Notes
- If you add a database or other service, update `docker-compose.yml` accordingly and ensure the application is configured to connect to it.
- The build skips tests for faster container builds. To include tests, modify the build stage in the Dockerfile.
