# Evolutionary Design

Quello che scrivi oggi è legacy domani.

Fare architetture per poterle cambiare. 
L'architettura emerge dal suo utilizzo in ambiente di produzione!

- refactoring con sempre la rete di salvezza dei test
- dividere per logiche di business e non per componenti
- tendere ad un concetto di componibilità a runtime: da branch strategy a feature strategy
- non aver paura di scrivere, se:
  - riscrivere sono poche centinaia di righe di codice
  - ci sono i test
  - ha un valore di business
  - ha una strategia di rollback nel caso fallisse la riscrittura

*Mantenere sempre aggiornata una visione dell'architettura di alto livello per condividerla con tutto il team ed essere sempre tutti allineati*
