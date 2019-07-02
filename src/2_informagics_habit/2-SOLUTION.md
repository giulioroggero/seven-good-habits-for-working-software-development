
# Informagica - Ecco il problema

```nginx
upstream hello {
    server hello;
}

server {
    listen 80;

    location / {
        proxy_pass http://hello;
    }
}
```

Comporta che se hello è spostato da un nodo all'altro, hello avrà un altro IP
Nginx fa la resolve dell'ip degli upstream allo startup e tiene la cache.
Per cui il problema magico che si risolve con un restart!

(trovato da Jacopo Giola)

**un altro esempio**: "accade solo con iPhone 7 Plus, capitava raramente, abbiamo anche testato sullo stesso device senza successo, poi si è capito che era un problema legato al locale!

Per evitare che il problema ritorni è importante cercare sempre le cause alla base!

- migliorare i log e creare degli analitici che consentano di vedere ogni quanto il problema accade (es: 499)
- replicare il problema creando test che lo evidenziano
- isolare il problema e renderlo evidente all'utente nel caso che non si riesca a fare nulla e chiedere all'utente di darci una mano
