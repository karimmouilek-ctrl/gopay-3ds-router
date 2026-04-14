# GoPay 3DS Router

Worldline GoPay — service de routing 3DS v2 pour l'acceptance online.
Fait partie de la plateforme cible GoPay dans le cadre du plan North Star 2030.

## Architecture
- `RouterStrategy` — interface stratégique pour le choix de la route 3DS
- `IssuerSpecificFallbackStrategy` — fallback ciblé par issuer en cas de dégradation ACS
- `FallbackPolicyLoader` — chargement des policies YAML versionnées avec cache TTL 30s
- `FallbackMetrics` — instrumentation Prometheus/OpenTelemetry

## Observabilité
- Dashboard Datadog : `gopay-3ds-router` (EU-West-1)
- AI-Skills Slack : `/explain-payment-failure`, `/recap-incident`, `/summarize-pr`
