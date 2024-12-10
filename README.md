Spring Multi-Tenant Application with JWT Authentication

Overview

This is a Spring Boot application designed to support a multi-tenancy setup using a schema-per-tenant architecture. The application ensures secure access to tenant-specific data using Spring Security and JWT tokens for authentication and authorization. By leveraging JPA for persistence and a custom Tenant Context, the application dynamically resolves the correct database schema based on the authenticated user's tenant information.

Features
1. Multi-Tenancy
Schema-Per-Tenant Architecture: Each tenant has its own dedicated schema, ensuring data isolation and better performance.
Dynamic Schema Resolution: The application dynamically resolves the database schema for each request using a custom tenant context.
2. Security
JWT Authentication: The application uses JSON Web Tokens (JWT) for secure and stateless authentication.
Tokens are signed using a secure key to ensure validity and integrity.
Role-Based Access Control: Ensures only authorized users can access specific resources.
Custom Authentication Filter: Intercepts and processes incoming requests to validate JWT tokens.
3. Persistence
JPA with Hibernate: Used for Object-Relational Mapping (ORM).
Custom Entity Manager: Configured to manage database connections for multiple schemas.
Flyway: (Optional) Manages schema migrations and versioning for each tenant.
4. Tenant Context Management
Tenant Resolver: Extracts tenant information from the JWT token or request headers.
Thread-Local Storage: Ensures the tenant context is set per request for correct schema resolution.
Technologies Used
Spring Boot: Framework for rapid development.
Spring Security: Provides robust authentication and authorization mechanisms.
JWT (JSON Web Token): For secure, stateless authentication.
JPA with Hibernate: For database interactions.
MySQL: Database for schema-per-tenant architecture.
Lombok: Reduces boilerplate code in entity and service classes.
Maven: For dependency management and build configuration.
Application Workflow
User Login:

A user logs in by providing their credentials.
The server authenticates the user and generates a JWT token, which includes tenant information in the payload.
Request Handling:

Each incoming request includes the JWT token in the Authorization header.
The application validates the token, extracts the tenant identifier, and sets it in the Tenant Context.
Dynamic Schema Resolution:

The Tenant Context dynamically selects the correct schema for the request.
All database queries and transactions are scoped to the tenant’s schema.
Response:

The server processes the request, retrieves tenant-specific data, and returns a secure response.
