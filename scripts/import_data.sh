#!/usr/bin/env bash
set -euo pipefail

# Simple data import helper for HackLoad datasets
# Imports SQL files (users.sql, events.sql, events_archive.sql) into the Docker Postgres service

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
INSTR_DIR="$REPO_ROOT/hackathon_instructions"

# Defaults match compose.yaml and application.yaml
: "${DB_USER:=billetter}"
: "${DB_NAME:=billetter-db}"
: "${DB_SERVICE:=postgres}"

echo "==> Ensuring Docker Compose service '$DB_SERVICE' is up..." >&2
(cd "$REPO_ROOT" && docker compose up -d "$DB_SERVICE")

if ! (cd "$REPO_ROOT" && docker compose ps "$DB_SERVICE" --status running --format=json >/dev/null 2>&1); then
  echo "Error: Docker service '$DB_SERVICE' is not running. Start it with: docker compose up -d" >&2
  exit 1
fi

find_sql() {
  local name="$1"
  # Look for the file anywhere under hackathon_instructions
  local match
  match=$(find "$INSTR_DIR" -type f -name "$name" -print 2>/dev/null | head -n1 || true)
  if [[ -n "${match:-}" ]]; then
    echo "$match"
  fi
}

import_sql() {
  local file="$1"
  local label="$2"
  echo "==> Importing $label from: $file" >&2
  # Pipe file contents into psql inside the container
  docker compose exec -T "$DB_SERVICE" \
    psql -U "$DB_USER" -d "$DB_NAME" -v ON_ERROR_STOP=1 -f - < "$file"
}

# Ensure minimal schema exists for datasets that ship only data
ensure_schema() {
  echo "==> Ensuring minimal schema exists (events_archive) ..." >&2
  docker compose exec -T "$DB_SERVICE" psql -U "$DB_USER" -d "$DB_NAME" -v ON_ERROR_STOP=1 -c "\
    CREATE TABLE IF NOT EXISTS events_archive (
      id BIGINT PRIMARY KEY,
      title TEXT NOT NULL,
      description TEXT,
      type TEXT,
      datetime_start TIMESTAMP,
      provider TEXT
    );
  "
}

missing=0
processed=0

# Import order matters: ensure events_archive exists, then import archive, events, then users
ensure_schema

FILES_ORDER=(
  "events_archive.sql:Events Archive"
  "events.sql:Events"
  "users.sql:Users"
)

for entry in "${FILES_ORDER[@]}"; do
  fname="${entry%%:*}"
  label="${entry#*:}"
  path="$(find_sql "$fname" || true)"
  if [[ -z "${path:-}" ]]; then
    echo "-- Skipping $label: $fname not found under $INSTR_DIR" >&2
    missing=$((missing+1))
    continue
  fi
  import_sql "$path" "$label"
  processed=$((processed+1))
done

echo "==> Import complete. Imported: $processed, Missing: $missing" >&2
echo "Tip: Place the official SQL files under $INSTR_DIR (any subfolder)." >&2
