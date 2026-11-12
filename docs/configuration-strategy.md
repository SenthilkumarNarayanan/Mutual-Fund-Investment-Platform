
# Environment Configuration Strategy

## 1. Objective

Establish a centralized and environment-specific configuration
standard for all Mutual Fund Investment Platform microservices.

## 2. Supported Environments

| Profile | Purpose |
|---------|---------|
| dev | Local development |
| test | Automated and integration testing |
| prod | Production deployment |

## 3. Configuration File Naming Convention

Each microservice must use the following structure:

- application.yml
- application-dev.yml
- application-test.yml
- application-prod.yml

## 4. Configuration Loading

Spring Boot uses application.yml as the common configuration file.

Environment-specific configuration is loaded using
the active Spring profile.

Example:

spring.profiles.active: dev

The active profile can also be supplied using:

SPRING_PROFILES_ACTIVE=dev

## 5. Database Configuration

Database connection properties must be externalized.

Example:

spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

## 6. Sensitive Information

- Never commit database passwords, API keys, tokens or secrets.
- Store sensitive values in environment variables.
- Use a secret manager or secure deployment environment
  for production credentials.
- Maintain .env.example with placeholder values only.
- Never commit actual .env files.

## 7. Environment-Specific Overrides

Environment-specific files may override common properties.

For example:
- Development may use a local database.
- Testing may use a dedicated test database.
- Production must use secured production infrastructure.

## 8. Naming Conventions

- Use lowercase kebab-case for service names.
- Use uppercase snake case for environment variables.
- Use Spring Boot standard property names.
- Use descriptive names for custom configuration properties.

Examples:

DB_URL
DB_USERNAME
DB_PASSWORD
SPRING_PROFILES_ACTIVE
PAYMENT_API_URL

## 9. Microservice Responsibilities

Every microservice must:
- Support dev, test and prod profiles.
- Externalize database and service connection details.
- Avoid hardcoded credentials.
- Follow the common configuration naming convention.

## 10. Security

Production secrets must be provided through secure
environment variables or a secrets management platform.
They must never be stored in source control.


## 9. Running Services with Environment Variables

### Windows CMD

Set the environment variables before starting a Spring Boot microservice:

```cmd
set SPRING_PROFILES_ACTIVE=dev
set SERVICE_NAME=user-service
set SERVER_PORT=8081
set DB_URL=jdbc:mysql://localhost:3306/mfip_dev
set DB_USERNAME=your_database_username
set DB_PASSWORD=your_local_password
set LOG_LEVEL=DEBUG

mvn spring-boot:run
```

### PowerShell

```powershell
$env:SPRING_PROFILES_ACTIVE="dev"
$env:SERVICE_NAME="user-service"
$env:SERVER_PORT="8081"
$env:DB_URL="jdbc:mysql://localhost:3306/mfip_dev"
$env:DB_USERNAME="your_database_username"
$env:DB_PASSWORD="your_local_password"
$env:LOG_LEVEL="DEBUG"

mvn spring-boot:run
```

### Important Notes

- Replace placeholder database credentials with local values.
- Never commit actual passwords, API keys, or other secrets.
- Environment variables configured using these commands apply only to the current terminal session.
- Production deployments must provide their own environment variables through the deployment platform's secure configuration settings.
- Each microservice must maintain its own application configuration files or explicitly import the shared configuration templates.