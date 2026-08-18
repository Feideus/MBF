# audiosync-backend

Foundation backend for the Audio-Synced Reading Platform (see the project
scoping document at the repository root). This slice sets up the layered
architecture and one real endpoint (`GET /api/v1/texts/{id}`) that exposes
information about an existing text. Tone analysis, audio matching and the
community library will be built as additional services on top of this base.

## Stack

- Java 21, Spring Boot 3.3 (Spring MVC / Spring Data JPA)
- PostgreSQL, schema managed by Flyway migrations
- springdoc-openapi for Swagger UI
- Maven

## Layers

| Layer | Package | Responsibility |
|---|---|---|
| Controller | `controller` | HTTP contracts (`@RestController`), request/response mapping only |
| Service | `service` | Business logic; controllers never touch the repository directly |
| Repository | `repository` | Spring Data JPA interfaces — persistence only |
| Domain | `domain` | JPA entities and enums — the actual data model |
| DTO | `dto` | API-facing request/response records, plus a mapper between entity and DTO |
| Exception | `exception` | Domain exceptions + a `@RestControllerAdvice` that turns them into a consistent JSON error shape |
| Config | `config` | Cross-cutting configuration (OpenAPI docs today; CORS, security, etc. later) |

A request flows `Controller -> Service -> Repository -> Database`, and the
`Service` layer is the only place that touches both the domain model and
business rules, which keeps the controller thin and the persistence layer
swappable.

## Running locally

1. Start Postgres:
   ```bash
   docker compose up -d
   ```
2. Run the app (Flyway migrates the schema and an `ApplicationRunner`
   seeds one example text on first boot):
   ```bash
   ./mvnw spring-boot:run
   ```
   (or `mvn spring-boot:run` if you don't have the wrapper generated yet —
   run `mvn -N wrapper:wrapper` once to add it)
3. API docs: http://localhost:8080/swagger-ui.html
4. List texts: `GET http://localhost:8080/api/v1/texts`
5. Get info about a text: `GET http://localhost:8080/api/v1/texts/{id}`
   (grab an `id` from the list response above)

Configuration is environment-driven — see `.env.example`.

## Endpoint added in this slice

`GET /api/v1/texts/{id}` returns metadata about a single text: title,
author, language, genre, source type, analysis status, word count, scene
count (null until tone analysis runs), a short excerpt, and timestamps.
It does not return the full text body, to keep the payload light — that
will get its own endpoint once reading/playback is built.

`GET /api/v1/texts` (list) is included alongside it so there's a way to
discover ids without a UI yet.

## About the seeded example text

The project asked for Albert Camus' *L'Étranger* as example content, but
that novel is still under copyright (Camus died in 1960; EU protection
runs to at least 70 years post-death, and French wartime-extension rules
can push individual works further — *L'Étranger* is not public domain).
It also isn't something this assistant has a reliable verbatim copy of to
reproduce.

Rather than ship copyrighted or inaccurate content, the seeded example
(`src/main/resources/seed/the-signal-at-quai-des-brumes.txt`) is a short
piece of **original crime fiction** written for this project, structured
into five clearly-delimited scenes (exposition, dialogue, rising tension,
action, resolution) so it doubles as a reasonable fixture for testing
scene segmentation and tone detection later. It is wholly original, so
there's no licensing question — swap it for a real public-domain text
(e.g. from Project Gutenberg) whenever convenient.

If you do want a real Gutenberg text, drop the `.txt` file in
`src/main/resources/seed/`, point `ExampleTextSeeder` at it, and update
the metadata in that class.
