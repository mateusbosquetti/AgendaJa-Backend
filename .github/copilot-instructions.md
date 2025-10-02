# Copilot Custom Instructions for Agenda Já

## Context

Agenda Já is a modular Spring Boot API for appointment management, enabling users to schedule with establishments.  
Users can simultaneously manage their own businesses, be employees, and act as clients in other establishments.  
The system supports complex roles and relationships, with a PostgreSQL database as the backend.

## Code Style

- **Language:** All code, comments, and identifiers must be in English.
- **Naming conventions:**
    - Classes, Enums: `PascalCase`
    - Methods, variables, fields: `camelCase`
    - DTOs, Records: `PascalCase` for types, `camelCase` for fields
- **Sensitive Data:** Always use environment variables for secrets/configuration.

## Project Structure

- `model/`
    - `dto/`
        - `request/` (All request DTOs as Java records)
        - `response/` (All response DTOs as Java records)
    - `entity/` (All JPA entities)
    - `compositekey/` (Composite key classes for entities, especially for many-to-many relationships)
    - `enums/` (Enums used as entity attributes)
- `service/` (Business logic)
- `repository/` (Spring Data JPA repositories)
- `controller/` (REST controllers)
- `config/` (Configuration, including Spring Security)
- `mapper/` (Entity-DTO mappers, one per entity, with static methods)

## Patterns & Practices

- **Entities:**
    - Extend a base entity with `createdAt`, `updatedAt`, and `disabled` attributes.
    - Use `@SQLDelete` and `@Where` to implement soft deletes (`disabled = true`).
    - All queries must filter out `disabled = true`.
- **Relationships:**
    - Avoid `@ManyToMany`.
    - Always create explicit join entities and use `@OneToMany` and `@ManyToOne`.
- **DTO Mapping:**
    - Use static methods in `mapper` classes for conversion between entities and DTOs.
- **Builders:**
    - Prefer the builder pattern for constructing entities and DTOs.
- **Pagination:**
    - For any `getAll` endpoints, implement and suggest pagination.
- **Spring Security:**
    - All code related to security should be placed in `config/`.
- **SOLID & Clean Code:**
    - Apply basic SOLID principles.
    - Code should be self-explanatory—avoid excessive comments.
    - No hacks or workarounds; prefer maintainable, readable solutions.
- **Performance:**
    - Write optimized queries.
    - Avoid N+1 problems.
    - Use lazy/eager loading appropriately.

## Suggestions for Copilot

- Favor code samples that follow the folder and naming conventions described above.
- Use Java records for all DTOs.
- Do not propose use of `@ManyToMany`; always suggest explicit join entities.
- Use builder pattern for entity creation.
- Suggest pagination on collection endpoints.
- Place security configuration only in `config/`.
- Avoid unnecessary comments; code should be readable without them.
- Prefer static methods for mappers.
- Respect separation of responsibilities: controller for HTTP, service for business, repository for persistence.
- Always use environment variables for sensitive configuration.

## Documentation & Comments

- Favor readable code over comments.
- Comments should only be used for clarifying **non-obvious logic**.

## Do Not Suggest

- Hardcoded secrets, credentials, or configuration in code.
- Excessive comments or commented-out code.
- Use of `@ManyToMany` annotation.
- Anti-patterns or “quick fixes” that compromise maintainability.

---

**Follow these instructions to generate code that is idiomatic, maintainable, and consistent with the Agenda Já project
standards.**