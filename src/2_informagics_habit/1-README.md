# Informagica

Ogni tanto accade che un problema accada e poi si risolve da solo senza interventi... con al massimo un riavvio dell'applicativo.

Si dice che si è risolto per magia.

Vi assicuro che l'informagica non esiste... purtroppo :-(

## Problema

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

**Se questo viene rilasciato su k8s cosa potrebbe accadere?**

Il problema non si presenta sempre, è randomico e si risolve con un restart del pod con nginx
