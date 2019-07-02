# Habit 1 - Autorepair - Soluzione

Cosa fa un software ~~*Robusto*~~ **Antifragile**?

- se non funziona più riparte riprendendo l'ultimo stato consistente
- se trova uno stato inconsistente fa revert all'ultimo stato inconsistente
- e' veloce a ripartire
- ha log chiari in modo da poter generare allarmi che consentono il ripristino di una versione precedente

## Cosa ci portiamo a casa

- Dynamic environments and distributed systems - like microservices - lead to a higher chance of failures.
- Services should fail separately, achieve graceful degradation to improve user experience.
- 70% of the outages are caused by changes, reverting code is not a bad thing.
- Fail fast and independently. Teams have no control over their service dependencies.
- Architectural patterns and techniques like caching, bulkheads, circuit breakers and rate-limiters help to build reliable microservices.
