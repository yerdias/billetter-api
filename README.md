Billetter API (HackLoad)

Quick Start
- Install Java 21 and Docker.
- Start PostgreSQL: `docker compose up -d` (from repo root).
- Run the app: `./mvnw spring-boot:run` (first `chmod +x mvnw` if needed).

Import Official Datasets
- Place the downloaded `users.sql`, `events.sql`, and `events_archive.sql` from the HackLoad data release into `hackathon_instructions/` (any subfolder is fine).
- Import into Docker Postgres:
  - `bash scripts/import_data.sh`
- Defaults used by the script:
  - DB service: `postgres` (from `compose.yaml`)
  - DB name: `billetter-db`
  - DB user: `billetter`
- You can override with env vars: `DB_USER`, `DB_NAME`, `DB_SERVICE`.

Provider Endpoints (env)
- Event Provider: set `EVENT_PROVIDER` (e.g., `https://hub.hackload.kz/event-provider/common`).
- Payment Provider: set `PAYMENT_ENDPOINT` (e.g., `https://hub.hackload.kz/payment-provider/common`).
- Merchant credentials (if required): `MERCHANT_ID`, `MERCHANT_PASSWORD`.

Notes
- The folder `hackathon_instructions/` is git-ignored and safe for local docs/data.
- The current codebase is a Spring Boot skeleton; API endpoints will be implemented to match the specification provided in the instructions.

