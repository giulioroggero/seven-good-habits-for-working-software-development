# Habit 1 - Autorepair

Cosa fa un software *Robusto*?

- se ci sono errori di I/O continua a funzionare
- gestisce in modo pulito tutte le condizioni di "edge case"
- quando è stressato non si blocca ma riduce le performance e consente il recupero del sistema
- altre???

Quale complessità bisogna affrontare per scrivere un software robusto?

- intercettare tutte le eccezioni
- rirpistinare lo stato consistente dei dati in caso di eccezioni
- ripristinare le connessioni ai db/servizi e liberare la memoria non utilizzata

ALTRE SOLUZIONI?
