# JM-SkillTagService

Skill tag management and reference service for the Job Manager platform.

## Overview

The Skill Tag Service provides a centralized repository of technical skills and competencies used across the platform. It maintains skill definitions that are referenced by Job Posts and Applicant Discovery search profiles.

## Features

- **Skill Tag Management**: Create, read, update, delete skill tags
- **Centralized Repository**: Single source of truth for skill definitions
- **Auto-Seeding**: Pre-populated with 20 common tech skills
- **Tag Search**: Search and filter skill tags
- **Usage Tracking**: Track which jobs/profiles use each skill
- **Category Support**: Group skills by category (future enhancement)

## Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**: Database persistence
- **PostgreSQL**: Skill tags database
- **Kafka**: Event-driven updates
- **Lombok**: Reduce boilerplate code

## Prerequisites

- Java 17 or higher
- Maven 3.8+
- PostgreSQL database
- Kafka broker (optional)

## Database Schema

### Table: `skill_tags`

| Column | Type               | Description          |
| ------ | ------------------ | -------------------- |
| `id`   | INTEGER (PK, Auto) | Skill tag identifier |
| `name` | VARCHAR            | Skill name (unique)  |

> **Note**: Uses auto-increment integer IDs for simplicity and efficient referencing.

## Data Seeding

The service automatically seeds **20 default skill tags** on startup:

| ID  | Skill Name    | Category                |
| --- | ------------- | ----------------------- |
| 1   | JAVA          | Programming Language    |
| 2   | PYTHON        | Programming Language    |
| 3   | JAVASCRIPT    | Programming Language    |
| 4   | TYPESCRIPT    | Programming Language    |
| 5   | REACT         | Frontend Framework      |
| 6   | ANGULAR       | Frontend Framework      |
| 7   | VUE           | Frontend Framework      |
| 8   | SPRING BOOT   | Backend Framework       |
| 9   | NODE.JS       | Backend Framework       |
| 10  | SNOWFLAKE     | Database/Data Platform  |
| 11  | MONGODB       | Database                |
| 12  | DOCKER        | DevOps/Containerization |
| 13  | KUBERNETES    | DevOps/Orchestration    |
| 14  | AWS           | Cloud Platform          |
| 15  | AZURE         | Cloud Platform          |
| 16  | COMMUNICATION | Soft Skill              |
| 17  | REST API      | API Technology          |
| 18  | GRAPHQL       | API Technology          |
| 19  | HTML          | Web Technology          |
| 20  | CSS           | Web Technology          |

## Skill Tag Usage Examples

### Software Engineering Jobs

- React (5), Spring Boot (8), Docker (12)
- Java (1), TypeScript (4), Kubernetes (13)

### Data Engineering Jobs

- Python (2), AWS (14), Snowflake (10)
- SQL, Spark, Airflow (can be added)

### Frontend Development

- React (5), TypeScript (4), HTML (19), CSS (20)
- JavaScript (3), Angular (6), Vue (7)

## Popular Skills by Domain

### Software Engineering

```
1. JAVA (1)
2. SPRING BOOT (8)
3. REACT (5)
4. DOCKER (12)
5. KUBERNETES (13)
```

### Data Engineering

```
1. PYTHON (2)
2. AWS (14)
3. SNOWFLAKE (10)
4. SPARK (future)
5. AIRFLOW (future)
```

### Frontend Development

```
1. REACT (5)
2. TYPESCRIPT (4)
3. HTML (19)
4. CSS (20)
5. JAVASCRIPT (3)
```

### DevOps

```
1. DOCKER (12)
2. KUBERNETES (13)
3. AWS (14)
4. JENKINS (future)
5. TERRAFORM (future)
```
