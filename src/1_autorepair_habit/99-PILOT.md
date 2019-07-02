# Qui tutto il codice che mi dimentico

```bash
set -a && source default.env
docker-compose up --build -d
```

```yml
HEALTHCHECK --start-period=5s --interval=5s --timeout=2s --retries=2 \
 CMD wget -qO- http://localhost:${HTTP_PORT}/-/healthz &> /dev/null || exit 1
```

```yml
autoheal:
    restart: always
    image: willfarrell/autoheal
    environment:
      - AUTOHEAL_CONTAINER_LABEL=all
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
```

```javascript
// eslint-disable-next-line no-unused-vars
module.exports.healthinessHandler = function healthinessHandler(fastify) {
  const statusOK = fastify.mongo.client && fastify.mongo.client.isConnected()
  const checkupResponse = statusOK ? { statusOK } : { statusOK, message: 'Mongo status is unhealthy' }

  return checkupResponse
}
```

```bash
curl http://localhost:3000/-/healthz | jsonpp
```

```
docker inspect 1_autorepair_habit_node_1 | jq ".[].State.Health"
```

```bash
curl -X POST -d '{"model": "ford cmx", "color": "blue", "quantity": 7}' -H 'content-type:application/json' http://localhost:3000/cars
curl http://localhost:3000/cars | jsonpp
```

```bash
 docker-compose up --build
 docker-compose ps
 docker-compose stop mongodb
```
