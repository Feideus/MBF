# MBF — Audio-Synced Reading Platform

Community-driven platform that analyzes a text scene by scene, detects
the tone of each scene, and matches it with a royalty-free audio track
for an immersive, synced reading experience. Full context, target users,
MVP scope and constraints: [`project-scoping-document.md`](./project-scoping-document.md).

## Repository layout

```
backend/              Spring Boot backend foundation (see backend/README.md)
examples/texts/       Example literature used for local dev/testing
project-scoping-document.md
```

## Backend

The backend foundation lives in [`backend/`](./backend) — a layered
Spring Boot + PostgreSQL service with a first endpoint exposing metadata
about an existing text. See [`backend/README.md`](./backend/README.md)
for the architecture, how to run it locally, and how it's seeded.

## Example text

[`examples/texts/the-signal-at-quai-des-brumes.txt`](./examples/texts/the-signal-at-quai-des-brumes.txt)
is an original short crime-fiction piece used as example content (the
same file is bundled into the backend and auto-seeded into the database
on first run). It stands in for Albert Camus' *L'Étranger*, which was
requested initially but is still under copyright and not usable under
the MVP's public-domain/non-proprietary text constraint — see the note
in `backend/README.md` for the full reasoning.
